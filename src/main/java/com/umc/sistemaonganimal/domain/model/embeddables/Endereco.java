package com.umc.sistemaonganimal.domain.model.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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

    @Column(nullable = false, length = 100)
    private String logradouro;

    @Column(nullable = false, length = 50)
    private String bairro;

    @Column(nullable = false, length = 50)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(name = "num_endereco", nullable = false, length = 10)
    private String numero;

    @Column(length = 50)
    private String complemento;
}
