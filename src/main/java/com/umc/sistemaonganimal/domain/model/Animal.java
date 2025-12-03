package com.umc.sistemaonganimal.domain.model;

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
//    TODO adicionar atributos sexo e porte e fazer mapeamento corretamente

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
