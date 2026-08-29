package com.umc.sistemaonganimal.api.dto.request;

import com.umc.sistemaonganimal.domain.model.Adotante;
import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.model.Raca;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalPorte;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalSexo;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
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
public class AnimalRequestDTO {

    @NotBlank
    private String nome;

    @PositiveOrZero
    private int idadeMeses;

    @NotNull
    private AnimalPorte porte;

    @NotNull
    private AnimalSexo sexo;

    @NotNull
    private AnimalStatus status;

    private boolean castrado;

    @PastOrPresent
    private LocalDate dataResgate;

    private LocalDate dataSaida;

    private String corOlhos;

    private String corPelagem;

    private String observacao;

    @NotNull
    private Long racaId;

    private Long adotanteId;

    public Animal toEntity() {
        return Animal.builder()
                .nome(nome)
                .idadeMeses(idadeMeses)
                .porte(porte)
                .sexo(sexo)
                .status(status)
                .castrado(castrado)
                .dataResgate(dataResgate)
                .dataSaida(dataSaida)
                .corOlhos(corOlhos)
                .corPelagem(corPelagem)
                .observacao(observacao)
                .raca(Raca.builder().id(racaId).build())
                .adotante(adotanteId != null ? Adotante.builder().id(adotanteId).build() : null)
                .build();
    }
}
