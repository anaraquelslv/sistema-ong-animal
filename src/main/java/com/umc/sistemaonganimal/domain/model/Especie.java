package com.umc.sistemaonganimal.domain.model;

import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalEspecie;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString()
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "especie")
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private AnimalEspecie nome;
}
