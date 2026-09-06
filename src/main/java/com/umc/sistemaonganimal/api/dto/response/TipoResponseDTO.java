package com.umc.sistemaonganimal.api.dto.response;

import com.umc.sistemaonganimal.domain.model.Tipo;
import com.umc.sistemaonganimal.domain.model.enums.general.TipoResponsavel;
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
public class TipoResponseDTO {

    private Long id;

    private TipoResponsavel nome;

    public static TipoResponseDTO fromEntity(Tipo tipo) {
        if (tipo == null) {
            return null;
        }
        return TipoResponseDTO.builder()
                .id(tipo.getId())
                .nome(tipo.getNome())
                .build();
    }
}
