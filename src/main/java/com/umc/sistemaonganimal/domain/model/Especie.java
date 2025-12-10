package com.umc.sistemaonganimal.domain.model;

import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalEspecie;
import jakarta.persistence.*;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalEspecie nome;
}
