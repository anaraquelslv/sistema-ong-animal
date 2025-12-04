package com.umc.sistemaonganimal.domain.model;

import com.umc.sistemaonganimal.domain.model.enums.AnimalPorte;
import com.umc.sistemaonganimal.domain.model.enums.AnimalSexo;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Column(nullable = false)
    private String nome;

//    TODO adicionar verificação se a idade é maior ou igual a 0
    @ToString.Include
    private int idade;

    @ToString.Include
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalPorte porte;

    @ToString.Include
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalSexo sexo;

    @ToString.Include
    @NotNull
    @Column(nullable = false)
    private boolean castrado;

    @NotNull
    @Column(name = "dt_resgate", nullable = false)
    private LocalDate dataResgate;

//  TODO adicionar restrição para que a data de saida nunca seja antes que a data de resgate
    @Column(name = "dt_saida")
    private LocalDate dataSaida;

    @Column(name = "cor_olhos")
    private String corOlhos;

    @Column(name = "cor_pelagem")
    private String corPelagem;

    private String observacao;

}
