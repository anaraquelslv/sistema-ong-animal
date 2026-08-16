package com.umc.sistemaonganimal.api.dto.response;

import com.umc.sistemaonganimal.api.dto.embeddables.ContatoDTO;
import com.umc.sistemaonganimal.api.dto.embeddables.DadosDemograficosDTO;
import com.umc.sistemaonganimal.api.dto.embeddables.DocumentoDTO;
import com.umc.sistemaonganimal.api.dto.embeddables.EnderecoDTO;
import com.umc.sistemaonganimal.domain.model.Adotante;
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
public class AdotanteResponseDTO {

    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private DocumentoDTO documento;

    private DadosDemograficosDTO dadosDemograficos;

    private ContatoDTO contato;

    private EnderecoDTO endereco;

    public static AdotanteResponseDTO fromEntity(Adotante adotante) {
        if (adotante == null) {
            return null;
        }
        return AdotanteResponseDTO.builder()
                .id(adotante.getId())
                .nome(adotante.getNome())
                .dataNascimento(adotante.getDataNascimento())
                .documento(DocumentoDTO.fromEntity(adotante.getDocumento()))
                .dadosDemograficos(DadosDemograficosDTO.fromEntity(adotante.getDadosDemograficos()))
                .contato(ContatoDTO.fromEntity(adotante.getContato()))
                .endereco(EnderecoDTO.fromEntity(adotante.getEndereco()))
                .build();
    }
}
