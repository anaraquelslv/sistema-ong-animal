package com.umc.sistemaonganimal.domain.model;

import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalPorte;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalSexo;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalStatus;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @Column(nullable = false, length = 50)
    private String nome;

//    TODO adicionar verificação se a idade é maior ou igual a 0
    @ToString.Include
    private int idade;

    @ToString.Include
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalPorte porte;

    @ToString.Include
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalSexo sexo;

    @ToString.Include
    @Column(nullable = false)
    @Enumerated
    private AnimalStatus status;

    @ToString.Include
    private boolean castrado;

//    TODO adicionar validação para que a data de resgate seja anterior a data atual
    @Column(name = "dt_resgate", nullable = false)
    private LocalDate dataResgate;

//  TODO adicionar restrição para que a data de saida nunca seja anterior a data de resgate
    @Column(name = "dt_saida")
    private LocalDate dataSaida;

    @Column(name = "cor_olhos", length = 20)
    private String corOlhos;

    @Column(name = "cor_pelagem", length = 20)
    private String corPelagem;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "raca_id", nullable = false)
    private Raca raca;

    @ManyToOne
    @JoinColumn(name = "adotante_id")
    private Adotante adotante;
}
