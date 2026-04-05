package com.umc.sistemaonganimal.domain.model.embeddables;

import com.umc.sistemaonganimal.domain.model.enums.general.Escolaridade;
import com.umc.sistemaonganimal.domain.model.enums.general.EstadoCivil;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Setter @Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Embeddable
public class DadosDemograficos {

    @Column(length = 50)
    private String profissao;

    @PositiveOrZero(message = "O valor da renda mensal deve ser positvo e maior que zero")
    @Column(name = "renda_mensal")
    private double rendaMensal;

    @Column(name = "estado_civil", length = 25)
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private Escolaridade escolaridade;
}
