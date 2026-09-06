package com.umc.sistemaonganimal.api.dto.response;

import com.umc.sistemaonganimal.api.dto.embeddables.ContatoDTO;
import com.umc.sistemaonganimal.api.dto.embeddables.DocumentoDTO;
import com.umc.sistemaonganimal.api.dto.embeddables.EnderecoDTO;
import com.umc.sistemaonganimal.domain.model.Responsavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsavelResponseDTO {

    private Long id;

    private String nome;

    private DocumentoDTO documento;

    private String cnpj;

    private ContatoDTO contato;

    private EnderecoDTO endereco;

    private Integer qtdAnimais;

    public static ResponsavelResponseDTO fromEntity(Responsavel responsavel) {
        if (responsavel == null) {
            return null;
        }
        return ResponsavelResponseDTO.builder()
                .id(responsavel.getId())
                .nome(responsavel.getNome())
                .documento(DocumentoDTO.fromEntity(responsavel.getDocumento()))
                .cnpj(responsavel.getCnpj())
                .contato(ContatoDTO.fromEntity(responsavel.getContato()))
                .endereco(EnderecoDTO.fromEntity(responsavel.getEndereco()))
                .qtdAnimais(responsavel.getQtdAnimais())
                .build();
    }
}
