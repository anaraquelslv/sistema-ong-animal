package com.umc.sistemaonganimal.api.dto.response;

import com.umc.sistemaonganimal.domain.model.Animal;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalPorte;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalSexo;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalStatus;
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
public class AnimalResponseDTO {

    private Long id;

    private String nome;

    private int idadeMeses;

    private AnimalPorte porte;

    private AnimalSexo sexo;

    private AnimalStatus status;

    private boolean castrado;

    private LocalDate dataResgate;

    private LocalDate dataSaida;

    private String corOlhos;

    private String corPelagem;

    private String observacao;

    private RacaResponseDTO raca;

    private AdotanteResponseDTO adotante;

    public static AnimalResponseDTO fromEntity(Animal animal) {
        if (animal == null) {
            return null;
        }
        return AnimalResponseDTO.builder()
                .id(animal.getId())
                .nome(animal.getNome())
                .idadeMeses(animal.getIdadeMeses())
                .porte(animal.getPorte())
                .sexo(animal.getSexo())
                .status(animal.getStatus())
                .castrado(animal.isCastrado())
                .dataResgate(animal.getDataResgate())
                .dataSaida(animal.getDataSaida())
                .corOlhos(animal.getCorOlhos())
                .corPelagem(animal.getCorPelagem())
                .observacao(animal.getObservacao())
                .raca(RacaResponseDTO.fromEntity(animal.getRaca()))
                .adotante(AdotanteResponseDTO.fromEntity(animal.getAdotante()))
                .build();
    }
}
