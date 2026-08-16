package com.umc.sistemaonganimal.api.dto.request;

import com.umc.sistemaonganimal.api.dto.embeddables.ContatoDTO;
import com.umc.sistemaonganimal.api.dto.embeddables.DadosDemograficosDTO;
import com.umc.sistemaonganimal.api.dto.embeddables.DocumentoDTO;
import com.umc.sistemaonganimal.api.dto.embeddables.EnderecoDTO;
import com.umc.sistemaonganimal.domain.model.Adotante;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdotanteRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    @Valid
    private DocumentoDTO documento;

    @Valid
    private DadosDemograficosDTO dadosDemograficos;

    @Valid
    private ContatoDTO contato;

    @Valid
    private EnderecoDTO endereco;

    public Adotante toEntity() {
        return Adotante.builder()
                .nome(nome)
                .dataNascimento(dataNascimento)
                .documento(documento != null ? documento.toEntity() : null)
                .dadosDemograficos(dadosDemograficos != null ? dadosDemograficos.toEntity() : null)
                .contato(contato != null ? contato.toEntity() : null)
                .endereco(endereco != null ? endereco.toEntity() : null)
                .build();
    }
}
