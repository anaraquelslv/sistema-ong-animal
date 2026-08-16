package com.umc.sistemaonganimal.api.dto.embeddables;

import com.umc.sistemaonganimal.domain.model.embeddables.Endereco;
import jakarta.validation.constraints.NotBlank;
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
public class EnderecoDTO {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    private String cep;

    @NotBlank
    private String numero;

    private String complemento;

    public static EnderecoDTO fromEntity(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return EnderecoDTO.builder()
                .logradouro(endereco.getLogradouro())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .build();
    }

    public Endereco toEntity() {
        return Endereco.builder()
                .logradouro(logradouro)
                .bairro(bairro)
                .cidade(cidade)
                .estado(estado)
                .cep(cep)
                .numero(numero)
                .complemento(complemento)
                .build();
    }
}
