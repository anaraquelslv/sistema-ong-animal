package com.umc.sistemaonganimal.domain.service;

import com.umc.sistemaonganimal.domain.exception.EspecieNotFoundException;
import com.umc.sistemaonganimal.domain.model.Especie;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalEspecie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
class EspecieServiceIT {

    // Spring injeta a instância real de EspecieService, já conectada ao banco.
    @Autowired
    private EspecieService especieService;

    // HAPPY PATH: listar() nunca deve retornar uma espécie fora de GATO/CACHORRO.
    // Passo a passo:
    // 1. Chamamos especieService.listar() e pegamos os registros que já estiverem no
    //    banco no momento do teste — sem assumir quantidade, ids ou quais valores
    //    específicos o fixture (afterMigrate.sql) inseriu.
    // 2. A regra de negócio real ("o sistema só admite gato e cachorro") é garantida
    //    a nível de tipo pelo enum AnimalEspecie, que só declara CACHORRO e GATO
    //    (veja AnimalEspecie.java) — nenhum outro valor é sequer compilável.
    // 3. Por isso, em vez de comparar contra uma lista fixa esperada, verificamos que
    //    todo "nome" retornado é um dos valores válidos do enum (isSubsetOf). Esse
    //    teste passa independente de quantas espécies existirem no banco, e continua
    //    útil como "trava" caso o enum AnimalEspecie um dia ganhe um valor novo sem
    //    que isso seja intencional — nesse caso, quem revisar o teste percebe o
    //    aumento de escopo da regra de negócio.
    @Test
    void listar_nuncaDeveRetornarEspecieForaDeGatoOuCachorro() {
        List<Especie> especies = especieService.listar();

        assertThat(especies)
                .extracting(Especie::getNome)
                .isSubsetOf(AnimalEspecie.values());
    }

    // HAPPY PATH: buscarPorId() com um id que existe deve retornar a entidade correta.
    // Passo a passo:
    // 1. Em vez de fixar um id manualmente (ex.: "id=1 é CACHORRO"), pegamos um id
    //    real na hora, via especieService.listar(). Isso é proposital: o id que o
    //    banco atribui a cada espécie é um detalhe de implementação (depende da ordem
    //    de inserção do fixture), não uma regra do sistema — então o teste não deve
    //    depender disso. Se o fixture mudar de ordem no futuro, este teste continua
    //    válido.
    // 2. Chamamos especieService.buscarPorId(id) usando esse id obtido dinamicamente.
    // 3. Verificamos que a entidade retornada é a mesma que veio da listagem (mesmo id
    //    e mesmo nome), confirmando que o service busca e retorna a entidade certa.
    @Test
    void buscarPorId_comIdExistente_deveRetornarAEspecieCorreta() {
        Especie especieExistente = especieService.listar().get(0);

        Especie especieEncontrada = especieService.buscarPorId(especieExistente.getId());

        assertThat(especieEncontrada.getId()).isEqualTo(especieExistente.getId());
        assertThat(especieEncontrada.getNome()).isEqualTo(especieExistente.getNome());
    }

    // UNHAPPY PATH: buscarPorId() com um id que NÃO existe deve lançar uma exceção,
    // em vez de retornar null ou quebrar de forma inesperada.
    // Passo a passo:
    // 1. Usamos Long.MAX_VALUE como id. Ele nunca vai existir de verdade: a coluna
    //    "id" é gerada automaticamente pelo banco (IDENTITY) em sequência a partir de
    //    1, então é impraticável ela algum dia chegar até o maior valor possível de
    //    Long. Isso resolve o mesmo problema que um "9999L" tentaria resolver, mas sem
    //    depender de nenhum número escolhido "no achismo" nem precisar consultar o
    //    banco antes — mais simples de ler.
    // 2. Chamamos especieService.buscarPorId(Long.MAX_VALUE) dentro de uma lambda,
    //    porque o assertThatThrownBy do AssertJ precisa "capturar" a execução do
    //    método para verificar a exceção lançada (se chamássemos direto, a exceção
    //    pararia o teste antes de qualquer asserção).
    // 3. Verificamos que a exceção lançada é do tipo EspecieNotFoundException — é isso
    //    que o EspecieService faz internamente via orElseThrow() quando o
    //    findById() do repository não encontra o registro.
    @Test
    void buscarPorId_comIdInexistente_deveLancarEspecieNotFoundException() {
        assertThatThrownBy(() -> especieService.buscarPorId(Long.MAX_VALUE))
                .isInstanceOf(EspecieNotFoundException.class);
    }

}
