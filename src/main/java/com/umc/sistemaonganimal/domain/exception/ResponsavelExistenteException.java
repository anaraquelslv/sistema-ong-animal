package com.umc.sistemaonganimal.domain.exception;

public class ResponsavelExistenteException extends EntityExistsException {
    public ResponsavelExistenteException(String message) {
        super(message);
    }

    public static ResponsavelExistenteException porEmail(String email) {
        return new ResponsavelExistenteException(
                String.format("Já existe um responsável cadastrado com o e-mail '%s'", email));
    }
}
