package com.umc.sistemaonganimal.domain.exception;

public class ResponsavelInUseException extends EntityInUseException {
    public ResponsavelInUseException(String message) {
        super(message);
    }

    public ResponsavelInUseException(Long id) {
        this(String.format("A entidade Responsável de código %d não pode ser removida pois está em uso", id));
    }
}
