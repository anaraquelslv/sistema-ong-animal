package com.umc.sistemaonganimal.domain.model;

import com.umc.sistemaonganimal.domain.model.enums.AnimalPorte;
import com.umc.sistemaonganimal.domain.model.enums.AnimalSexo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    private String nome;

    @ToString.Include
    private int idade;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    private AnimalPorte porte;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    private AnimalSexo sexo;

    @ToString.Include
    private boolean castrado;

    @Column(name = "dt_resgate")
    private LocalDate dataResgate;

    @Column(name = "dt_saida")
    private LocalDate dataSaida;

    @Column(name = "cor_olhos")
    private String corOlhos;

    @Column(name = "cor_pelagem")
    private String corPelagem;

    private String observacao;

}
