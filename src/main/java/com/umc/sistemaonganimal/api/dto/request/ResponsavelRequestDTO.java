package com.umc.sistemaonganimal.api.dto.request;

import com.umc.sistemaonganimal.api.dto.embeddables.ContatoDTO;
import com.umc.sistemaonganimal.api.dto.embeddables.DocumentoDTO;
import com.umc.sistemaonganimal.api.dto.embeddables.EnderecoDTO;
import com.umc.sistemaonganimal.domain.model.Responsavel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsavelRequestDTO {

    @NotBlank
    private String nome;

    // @Valid roda no grupo Default: valida o formato do CPF (@CPF) quando presente,
    // mas não exige (o @NotBlank do cpf só é checado no grupo Groups.CpfObrigatorio,
    // usado por AdotanteController) — aqui CPF é opcional (mutuamente exclusivo com CNPJ).
    @Valid
    private DocumentoDTO documento;

    @CNPJ
    private String cnpj;

    @NotNull
    @Valid
    private ContatoDTO contato;

    @NotNull
    @Valid
    private EnderecoDTO endereco;

    @PositiveOrZero
    private Integer qtdAnimais;

    public Responsavel toEntity() {
        return Responsavel.builder()
                .nome(nome)
                .documento(documento != null ? documento.toEntity() : null)
                .cnpj(cnpj)
                .contato(contato != null ? contato.toEntity() : null)
                .endereco(endereco != null ? endereco.toEntity() : null)
                .qtdAnimais(qtdAnimais)
                .build();
    }
}
