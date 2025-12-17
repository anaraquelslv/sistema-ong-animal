package com.umc.sistemaonganimal.domain.model.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Embeddable
public class Documento {

    @NotNull
    @Column(nullable = false, length = 11)
    private String cpf;

    @NotNull
    @Column(nullable = false, length = 20)
    private String rg;

    @NotNull
    @Column(nullable = false, length = 10)
    private String orgaoRg;
}
