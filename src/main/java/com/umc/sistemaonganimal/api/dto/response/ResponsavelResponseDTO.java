package com.umc.sistemaonganimal.api.dto.response;

import com.umc.sistemaonganimal.api.dto.embeddables.ContatoDTO;
import com.umc.sistemaonganimal.api.dto.embeddables.DocumentoDTO;
import com.umc.sistemaonganimal.api.dto.embeddables.EnderecoDTO;
import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.model.Responsavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    private TipoResponseDTO tipo;

    private List<AnimalResponseDTO> animaisVinculados;

    public static ResponsavelResponseDTO fromEntity(Responsavel responsavel) {
        return fromEntity(responsavel, null);
    }

    public static ResponsavelResponseDTO fromEntity(Responsavel responsavel, List<Animal> animaisVinculados) {
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
                .tipo(TipoResponseDTO.fromEntity(responsavel.getTipo()))
                .animaisVinculados(animaisVinculados != null
                        ? animaisVinculados.stream().map(AnimalResponseDTO::fromEntity).toList()
                        : null)
                .build();
    }
}
