package com.umc.sistemaonganimal.domain.model.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Embeddable
public class Documento {

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "Adicione um número válido de CPF")
    @Column(nullable = false, length = 11)
    private String cpf;

    @NotBlank(message = "O RG é obrigatório")
    @Column(nullable = false, length = 20)
    private String rg;

    @NotBlank(message = "O orgão emissor é obrigatório")
    @Column(nullable = false, length = 10)
    private String orgaoRg;
}
