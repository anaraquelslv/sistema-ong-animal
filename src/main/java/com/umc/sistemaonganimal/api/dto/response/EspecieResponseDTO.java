package com.umc.sistemaonganimal.api.dto.response;

import com.umc.sistemaonganimal.domain.model.Especie;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalEspecie;
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
public class EspecieResponseDTO {

    private Long id;

    private AnimalEspecie nome;

    public static EspecieResponseDTO fromEntity(Especie especie) {
        if (especie == null) {
            return null;
        }
        return EspecieResponseDTO.builder()
                .id(especie.getId())
                .nome(especie.getNome())
                .build();
    }
}
