package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.exception.AdotanteInUseException;
import com.umc.sistemaonganimal.domain.exception.AdotanteNotFoundException;
import com.umc.sistemaonganimal.domain.model.Adotante;
import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.model.Raca;
import com.umc.sistemaonganimal.domain.model.embeddables.Contato;
import com.umc.sistemaonganimal.domain.model.embeddables.DadosDemograficos;
import com.umc.sistemaonganimal.domain.model.embeddables.Documento;
import com.umc.sistemaonganimal.domain.model.embeddables.Endereco;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalPorte;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalSexo;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalStatus;
import com.umc.sistemaonganimal.domain.model.enums.general.Escolaridade;
import com.umc.sistemaonganimal.domain.model.enums.general.EstadoCivil;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// @SpringBootTest sobe o contexto Spring completo (services, repositories, JPA, Flyway),
// exatamente como quando a aplicação roda de verdade — por isso é um teste de INTEGRAÇÃO
// e não um teste unitário (que mockaria as dependências em vez de usá-las de verdade).
@SpringBootTest
// Ativa o profile "local" (application-local.properties), que aponta para o Postgres
// que já roda na máquina do dev (localhost:5433). Não usamos um banco separado de teste.
@ActiveProfiles("local")
// Faz cada método de teste rodar dentro de uma transação que é revertida (rollback)
// automaticamente ao final do método. Isso garante que os testes não deixem sujeira
// no banco e não interfiram uns nos outros, mesmo usando o banco real do dev.
@Transactional
class AdotanteServiceIT {

    // Spring injeta a instância real de AdotanteService, já conectada ao banco.
    @Autowired
    private AdotanteService adotanteService;

    // Usado no teste que precisa criar um Animal vinculado ao Adotante, para
    // simular o cenário de "adotante em uso".
    @Autowired
    private AnimalService animalService;

    // Usado só para obter o id de uma raça já existente no banco, sem depender de
    // qual raça específica é (evita acoplar o teste ao conteúdo do fixture).
    @Autowired
    private RacaService racaService;

    // Injeta o EntityManager (JPA) diretamente, para poder limpar o contexto de
    // persistência entre a exclusão e a busca seguinte (ver comentário no primeiro
    // teste abaixo que usa flush()/clear()).
    @Autowired
    private EntityManager entityManager;

    // Método auxiliar que cria e persiste um Adotante novo, isolado dos dados do
    // fixture, para que cada teste tenha seu próprio registro para excluir.
    private Adotante criarAdotante() {
        // Monta um Adotante válido em memória, preenchendo todos os embeddables
        // obrigatórios (Documento, DadosDemograficos, Contato, Endereco). CPF e
        // email usados aqui são diferentes dos que já existem no fixture
        // (afterMigrate.sql), para não colidir com as constraints UNIQUE do banco.
        Adotante adotante = Adotante.builder()
                .nome("Adotante de teste")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .documento(Documento.builder().cpf("98765432100").rg("987654321").orgaoRg("SSP").build())
                .dadosDemograficos(DadosDemograficos.builder()
                        .profissao("Testador")
                        .rendaMensal(3000.0)
                        .estadoCivil(EstadoCivil.SOLTEIRO)
                        .escolaridade(Escolaridade.SUPERIOR_COMPLETO)
                        .build())
                .contato(Contato.builder()
                        .telefonePrincipal("11988887777")
                        .telefoneSecundario("11988887778")
                        .email("adotante.teste@example.com")
                        .build())
                .endereco(Endereco.builder()
                        .logradouro("Rua de teste")
                        .bairro("Bairro teste")
                        .cidade("Cidade teste")
                        .estado("SP")
                        .cep("01001000")
                        .numero("1")
                        .build())
                .build();

        // Persiste o Adotante via service (não direto no repository) para passar
        // pelas mesmas regras que a aplicação real aplicaria ao salvar.
        return adotanteService.salvar(adotante);
    }

    // HAPPY PATH: excluir() deve fazer exclusão LÓGICA — o registro some das buscas,
    // mas continua existindo fisicamente na tabela (não é um DELETE físico).
    // Passo a passo:
    // 1. Cria um Adotante novo (isolado do fixture), sem nenhum Animal vinculado a
    //    ele, para excluir neste teste.
    // 2. Chama adotanteService.excluir(id), que internamente verifica que não há
    //    Animal vinculado e então marca "ativo = false" e salva a entidade (em vez
    //    de apagar a linha do banco).
    // 3. Chama entityManager.flush() (envia o UPDATE pendente para o banco) e depois
    //    entityManager.clear() (esvazia o cache de 1º nível da sessão JPA). Isso é
    //    necessário porque @SQLRestriction("ativo = true") só filtra quando o
    //    Hibernate precisa ir ao banco — se a entidade já estiver em memória no
    //    contexto de persistência (como ficaria logo após o passo 2, na mesma
    //    transação), findById devolveria ela direto do cache, sem passar pelo
    //    filtro. Fazendo flush+clear, simulamos o que aconteceria de verdade em uma
    //    request HTTP nova (com um EntityManager novo, sem cache prévio).
    // 4. Verificamos que buscarPorId(id) agora lança AdotanteNotFoundException, ou
    //    seja, o Adotante excluído não é mais "encontrável" pela aplicação.
    // 5. Verificamos também que listar() não traz mais o id excluído na lista.
    @Test
    void excluir_semAnimalVinculado_devePararDeAparecerEmBuscaEListagem() {
        Adotante adotanteCriado = criarAdotante();

        adotanteService.excluir(adotanteCriado.getId());
        entityManager.flush();
        entityManager.clear();

        assertThatThrownBy(() -> adotanteService.buscarPorId(adotanteCriado.getId()))
                .isInstanceOf(AdotanteNotFoundException.class);
        assertThat(adotanteService.listar())
                .extracting(Adotante::getId)
                .doesNotContain(adotanteCriado.getId());

        // Prova de que é exclusão LÓGICA, não física: consultamos a tabela via SQL
        // nativo, contornando o @SQLRestriction (que só existe no mapeamento JPA da
        // entidade Adotante, não no banco em si). Se getSingleResult() não lançar
        // NoResultException, a linha ainda existe fisicamente; e o valor de "ativo"
        // deve ter sido marcado como false pelo excluir().
        Boolean ativo = (Boolean) entityManager
                .createNativeQuery("SELECT ativo FROM adotante WHERE id = :id")
                .setParameter("id", adotanteCriado.getId())
                .getSingleResult();
        assertThat(ativo).isFalse();
    }

    // UNHAPPY PATH: um Adotante com um Animal ativo vinculado (status ADOTADO) não
    // pode ser excluído. Antes, essa checagem vinha "de graça" da FK do banco (o
    // DELETE físico falhava com DataIntegrityViolationException); agora que a
    // exclusão é lógica (um UPDATE, não um DELETE), a FK não impede mais nada
    // sozinha — por isso o service passou a checar isso explicitamente, via
    // AnimalRepository.existsByAdotanteId, antes de desativar o Adotante.
    // Passo a passo:
    // 1. Cria um Adotante novo, isolado do fixture.
    // 2. Pega uma raça qualquer já existente, só para satisfazer o vínculo
    //    obrigatório Animal -> Raca.
    // 3. Cria e salva um Animal com status ADOTADO, vinculado ao Adotante criado no
    //    passo 1 — reproduzindo, dentro do próprio teste, o estado necessário para
    //    o cenário (em vez de depender de um vínculo já existente no fixture).
    // 4. Chama adotanteService.excluir() para esse Adotante e verifica que ele
    //    lança AdotanteInUseException, em vez de excluir o registro.
    @Test
    void excluir_comAnimalAdotadoVinculado_deveLancarAdotanteInUseException() {
        Adotante adotanteCriado = criarAdotante();
        Raca racaExistente = racaService.listar().get(0);

        Animal animal = Animal.builder()
                .nome("Animal adotado de teste")
                .idade(2)
                .porte(AnimalPorte.MEDIO)
                .sexo(AnimalSexo.FEMEA)
                .status(AnimalStatus.ADOTADO)
                .castrado(true)
                .dataResgate(LocalDate.now().minusMonths(1))
                .dataSaida(LocalDate.now())
                .raca(Raca.builder().id(racaExistente.getId()).build())
                .adotante(Adotante.builder().id(adotanteCriado.getId()).build())
                .build();
        animalService.salvar(animal);

        assertThatThrownBy(() -> adotanteService.excluir(adotanteCriado.getId()))
                .isInstanceOf(AdotanteInUseException.class);
    }

    // UNHAPPY PATH: excluir() com um id que não existe (nem nunca vai existir, por
    // ser Long.MAX_VALUE — ver explicação equivalente em EspecieServiceIT) deve
    // lançar AdotanteNotFoundException, em vez de falhar silenciosamente ou de
    // forma inesperada. Isso é garantido porque excluir() chama buscarPorId(id) por
    // dentro antes de tentar desativar o registro.
    @Test
    void excluir_comIdInexistente_deveLancarAdotanteNotFoundException() {
        assertThatThrownBy(() -> adotanteService.excluir(Long.MAX_VALUE))
                .isInstanceOf(AdotanteNotFoundException.class);
    }

    // UNHAPPY PATH: excluir() sobre um id que já foi excluído antes deve se
    // comportar como um id inexistente (AdotanteNotFoundException), e não permitir
    // "excluir de novo" silenciosamente. Isso acontece porque, depois do
    // flush()+clear(), o @SQLRestriction faz esse Adotante desaparecer de qualquer
    // busca subsequente — inclusive do buscarPorId() interno da segunda chamada a
    // excluir().
    @Test
    void excluir_comIdJaExcluido_deveLancarAdotanteNotFoundException() {
        Adotante adotanteCriado = criarAdotante();

        adotanteService.excluir(adotanteCriado.getId());
        entityManager.flush();
        entityManager.clear();

        assertThatThrownBy(() -> adotanteService.excluir(adotanteCriado.getId()))
                .isInstanceOf(AdotanteNotFoundException.class);
    }

    // HAPPY PATH: listar() deve refletir corretamente um cenário misto — com um
    // Adotante ativo e outro excluído lado a lado — trazendo só o ativo.
    // Passo a passo:
    // 1. Cria dois Adotantes novos e isolados: um que vai continuar ativo
    //    (adotanteMantido) e outro que será excluído (adotanteExcluido).
    // 2. Exclui apenas o segundo, e faz flush()+clear() para forçar a próxima
    //    chamada a listar() a ir ao banco (mesmo motivo do teste de exclusão acima).
    // 3. Verifica que a lista resultante contém o id do Adotante mantido e não
    //    contém o id do Adotante excluído — provando que o filtro do
    //    @SQLRestriction distingue corretamente entre os dois, e não é apenas
    //    "sorte" de o excluído não estar lá por outro motivo.
    @Test
    void listar_comRegistroExcluido_deveConterApenasOsAtivos() {
        Adotante adotanteMantido = criarAdotante();

        // Precisa de um segundo Adotante com CPF/email diferentes do primeiro, já
        // que ambos têm colunas UNIQUE no banco.
        Adotante adotanteExcluido = adotanteService.salvar(Adotante.builder()
                .nome("Adotante de teste 2")
                .dataNascimento(LocalDate.of(1985, 6, 15))
                .documento(Documento.builder().cpf("11122233344").rg("111222333").orgaoRg("SSP").build())
                .dadosDemograficos(DadosDemograficos.builder()
                        .profissao("Testador 2")
                        .rendaMensal(4000.0)
                        .estadoCivil(EstadoCivil.CASADO)
                        .escolaridade(Escolaridade.MESTRADO)
                        .build())
                .contato(Contato.builder()
                        .telefonePrincipal("11977776666")
                        .telefoneSecundario("11977776667")
                        .email("adotante.teste2@example.com")
                        .build())
                .endereco(Endereco.builder()
                        .logradouro("Rua de teste 2")
                        .bairro("Bairro teste 2")
                        .cidade("Cidade teste 2")
                        .estado("SP")
                        .cep("01001001")
                        .numero("2")
                        .build())
                .build());

        adotanteService.excluir(adotanteExcluido.getId());
        entityManager.flush();
        entityManager.clear();

        assertThat(adotanteService.listar())
                .extracting(Adotante::getId)
                .contains(adotanteMantido.getId())
                .doesNotContain(adotanteExcluido.getId());
    }
}
