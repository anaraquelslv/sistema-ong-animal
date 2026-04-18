package com.umc.sistemaonganimal.domain.model;

import com.umc.sistemaonganimal.core.validation.Groups;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalPorte;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalSexo;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
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

    @NotBlank
    @ToString.Include
    @Column(nullable = false, length = 50)
    private String nome;

    @PositiveOrZero
    @ToString.Include
    private int idade;

    @NotNull
    @ToString.Include
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalPorte porte;

    @NotNull
    @ToString.Include
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalSexo sexo;

    @NotNull
    @ToString.Include
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalStatus status;

    @ToString.Include
    private boolean castrado;

    @PastOrPresent
    @Column(name = "dt_resgate", nullable = false)
    private LocalDate dataResgate;

    // TODO adicionar restrição para que a data de saida nunca seja anterior a data
    // de resgate
    @Column(name = "dt_saida")
    private LocalDate dataSaida;

    @Column(name = "cor_olhos", length = 20)
    private String corOlhos;

    @Column(name = "cor_pelagem", length = 20)
    private String corPelagem;

    private String observacao;

    @NotNull
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.RacaId.class)
    @ManyToOne
    @JoinColumn(name = "raca_id", nullable = false)
    private Raca raca;

    @ManyToOne
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.AdotanteId.class)
    @JoinColumn(name = "adotante_id")
    private Adotante adotante;
}
