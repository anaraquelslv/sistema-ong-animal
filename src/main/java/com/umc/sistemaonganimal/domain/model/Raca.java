package com.umc.sistemaonganimal.domain.model;

import com.umc.sistemaonganimal.core.validation.Groups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.*;


@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "raca")
public class Raca {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Groups.RacaId.class)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 50)
    private String nome;


    @NotNull
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.EspecieId.class)
    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;
}
