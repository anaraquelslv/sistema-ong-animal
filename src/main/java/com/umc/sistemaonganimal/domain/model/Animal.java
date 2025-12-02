package com.umc.sistemaonganimal.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "animal")
public class Animal {
//    TODO adicionar atributos sexo e porte

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
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
