package com.umc.sistemaonganimal.domain.model.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode

@Embeddable
public class Endereco {

    @NotNull
    @Column(nullable = false, length = 100)
    private String logradouro;

    @NotNull
    @Column(nullable = false, length = 50)
    private String bairro;

    @NotNull
    @Column(nullable = false, length = 50)
    private String cidade;

    @NotNull
    @Column(nullable = false, length = 2)
    private String estado;

    @NotNull
    @Column(nullable = false, length = 8)
    private String cep;

    @NotNull
    @Column(name = "num_endereco", nullable = false, length = 10)
    private String numero;

    @Column(length = 50)
    private String complemento;
}
