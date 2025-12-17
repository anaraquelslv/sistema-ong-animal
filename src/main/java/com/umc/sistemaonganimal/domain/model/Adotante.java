package com.umc.sistemaonganimal.domain.model;

import com.umc.sistemaonganimal.domain.model.embeddables.Contato;
import com.umc.sistemaonganimal.domain.model.embeddables.DadosDemograficos;
import com.umc.sistemaonganimal.domain.model.embeddables.Documento;
import com.umc.sistemaonganimal.domain.model.embeddables.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "Adotante")
public class Adotante {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do adotante é obrigatório")
    @Column(nullable = false, length = 100)
    private String nome;

//    TODO adicionar validação de idade
    @NotNull(message = "A data de nascimento é obrigatória")
    @Column(name = "data_nasc", nullable = false)
    private LocalDate dataNascimento;

    @Embedded
    private Documento documento;

    @Embedded
    private DadosDemograficos dadosDemograficos;

    @Embedded
    private Contato contato;

    @Embedded
    private Endereco endereco;


}
