package com.umc.sistemaonganimal.api.dto.embeddables;

import com.umc.sistemaonganimal.domain.model.embeddables.DadosDemograficos;
import com.umc.sistemaonganimal.domain.model.enums.general.Escolaridade;
import com.umc.sistemaonganimal.domain.model.enums.general.EstadoCivil;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosDemograficosDTO {

    private String profissao;

    @PositiveOrZero(message = "O valor da renda mensal deve ser positvo e maior que zero")
    private double rendaMensal;

    private EstadoCivil estadoCivil;

    private Escolaridade escolaridade;

    public static DadosDemograficosDTO fromEntity(DadosDemograficos dadosDemograficos) {
        if (dadosDemograficos == null) {
            return null;
        }
        return DadosDemograficosDTO.builder()
                .profissao(dadosDemograficos.getProfissao())
                .rendaMensal(dadosDemograficos.getRendaMensal())
                .estadoCivil(dadosDemograficos.getEstadoCivil())
                .escolaridade(dadosDemograficos.getEscolaridade())
                .build();
    }

    public DadosDemograficos toEntity() {
        return DadosDemograficos.builder()
                .profissao(profissao)
                .rendaMensal(rendaMensal)
                .estadoCivil(estadoCivil)
                .escolaridade(escolaridade)
                .build();
    }
}
