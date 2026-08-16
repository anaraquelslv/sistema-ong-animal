package com.umc.sistemaonganimal.api.dto.response;

import com.umc.sistemaonganimal.domain.model.Raca;
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
public class RacaResponseDTO {

    private Long id;

    private String nome;

    private EspecieResponseDTO especie;

    public static RacaResponseDTO fromEntity(Raca raca) {
        if (raca == null) {
            return null;
        }
        return RacaResponseDTO.builder()
                .id(raca.getId())
                .nome(raca.getNome())
                .especie(EspecieResponseDTO.fromEntity(raca.getEspecie()))
                .build();
    }
}
