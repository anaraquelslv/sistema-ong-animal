package com.umc.sistemaonganimal.domain.model;

import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "status_animal")
public class StatusAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private AnimalStatus animalStatus;

    private boolean devolvido;

    @Column(name = "dt_atualizacao")
    private LocalDateTime data_atualizacao = LocalDateTime.now();


}
