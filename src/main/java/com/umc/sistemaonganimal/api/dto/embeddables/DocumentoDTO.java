package com.umc.sistemaonganimal.api.dto.embeddables;

import com.umc.sistemaonganimal.domain.model.embeddables.Documento;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentoDTO {

    @NotBlank
    @CPF
    private String cpf;

    private String rg;

    private String orgaoRg;

    public static DocumentoDTO fromEntity(Documento documento) {
        if (documento == null) {
            return null;
        }
        return DocumentoDTO.builder()
                .cpf(documento.getCpf())
                .rg(documento.getRg())
                .orgaoRg(documento.getOrgaoRg())
                .build();
    }

    public Documento toEntity() {
        return Documento.builder()
                .cpf(cpf)
                .rg(rg)
                .orgaoRg(orgaoRg)
                .build();
    }
}
