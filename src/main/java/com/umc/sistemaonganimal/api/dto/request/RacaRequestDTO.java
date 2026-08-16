package com.umc.sistemaonganimal.api.dto.request;

import com.umc.sistemaonganimal.domain.model.Especie;
import com.umc.sistemaonganimal.domain.model.Raca;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class RacaRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private Long especieId;

    public Raca toEntity() {
        return Raca.builder()
                .nome(nome)
                .especie(Especie.builder().id(especieId).build())
                .build();
    }
}
