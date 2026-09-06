package com.umc.sistemaonganimal.domain.model;

import com.umc.sistemaonganimal.domain.model.embeddables.Contato;
import com.umc.sistemaonganimal.domain.model.embeddables.Documento;
import com.umc.sistemaonganimal.domain.model.embeddables.Endereco;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "responsavel")
@SQLRestriction("ativo = true")
public class Responsavel {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Embedded
    private Documento documento;

    @Column(length = 14)
    private String cnpj;

    @Embedded
    private Contato contato;

    @Embedded
    private Endereco endereco;

    @Column(name = "qtd_animais")
    private Integer qtdAnimais;

    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    private Tipo tipo;

    @Builder.Default
    @Column(nullable = false)
    private boolean ativo = true;

}
