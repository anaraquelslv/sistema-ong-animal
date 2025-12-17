package com.umc.sistemaonganimal.domain.model.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Embeddable
public class Contato {

    @NotNull
    @Column(name = "telefone1", length = 15, nullable = false)
    private String telefonePrincipal;

    @NotNull
    @Column(name = "telefone2", length = 15, nullable = false)
    private String telefoneSecundario;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 50)
    private String instagram;
}
