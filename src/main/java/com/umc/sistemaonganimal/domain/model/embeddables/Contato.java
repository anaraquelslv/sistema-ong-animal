package com.umc.sistemaonganimal.domain.model.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Embeddable
public class Contato {

    @Column(name = "telefone1", length = 15, nullable = false)
    private String telefonePrincipal;

    @Column(name = "telefone2", length = 15)
    private String telefoneSecundario;

    // TODO Adicionar validação Unique e verificar resposta da exceção
    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 50)
    private String instagram;
}
