package com.umc.sistemaonganimal.api.dto.embeddables;

import com.umc.sistemaonganimal.domain.model.embeddables.Contato;
import jakarta.validation.constraints.Email;
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
public class ContatoDTO {

    @NotBlank
    private String telefonePrincipal;

    @NotBlank
    private String telefoneSecundario;

    @Email
    private String email;

    private String instagram;

    public static ContatoDTO fromEntity(Contato contato) {
        if (contato == null) {
            return null;
        }
        return ContatoDTO.builder()
                .telefonePrincipal(contato.getTelefonePrincipal())
                .telefoneSecundario(contato.getTelefoneSecundario())
                .email(contato.getEmail())
                .instagram(contato.getInstagram())
                .build();
    }

    public Contato toEntity() {
        return Contato.builder()
                .telefonePrincipal(telefonePrincipal)
                .telefoneSecundario(telefoneSecundario)
                .email(email)
                .instagram(instagram)
                .build();
    }
}
