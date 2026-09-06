package com.umc.sistemaonganimal.domain.model.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Embeddable
public class Documento {

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(length = 20)
    private String rg;

    @Column(length = 10)
    private String orgaoRg;
}
