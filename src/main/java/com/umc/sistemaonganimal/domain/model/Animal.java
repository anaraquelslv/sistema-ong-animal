package com.umc.sistemaonganimal.domain.model;

import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalPorte;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalSexo;
import com.umc.sistemaonganimal.domain.model.enums.animal.AnimalStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotBlank(message = "O nome é obrigatório")
    @ToString.Include
    @Column(nullable = false, length = 50)
    private String nome;

    @PositiveOrZero(message = "A idade deve ser maior ou igual zero")
    @ToString.Include
    private int idade;

    @NotNull(message = "O porte é obrigatório")
    @ToString.Include
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalPorte porte;

    @NotNull(message = "O sexo é obrigatório")
    @ToString.Include
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalSexo sexo;

    @NotNull(message = "O status é obrigatório")
    @ToString.Include
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimalStatus status;

    @ToString.Include
    private boolean castrado;

    @PastOrPresent(message = "A data de resgate deve ser anterior a data atual")
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "raca_id", nullable = false)
    private Raca raca;

    @ManyToOne
    @JoinColumn(name = "adotante_id")
    private Adotante adotante;
}
