package com.umc.sistemaonganimal.domain.model.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode

@Embeddable
public class Endereco {

    @NotBlank(message = "O logradouro é obrigatório")
    @Column(nullable = false, length = 100)
    private String logradouro;

    @NotBlank(message = "O bairro é obrigatório")
    @Column(nullable = false, length = 50)
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória")
    @Column(nullable = false, length = 50)
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    @Column(nullable = false, length = 2)
    private String estado;

    @NotBlank(message = "O CEP é obrigatório")
    @Column(nullable = false, length = 8)
    private String cep;

    @NotBlank(message = "O número é obrigatório")
    @Column(name = "num_endereco", nullable = false, length = 10)
    private String numero;

    @Column(length = 50)
    private String complemento;
}
