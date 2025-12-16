package com.umc.sistemaonganimal.domain.exception;

public class AdotanteInUseException extends EntityInUseException {
    public AdotanteInUseException(String message) {
        super(message);
    }

    public AdotanteInUseException(Long id) {
        this(String.format("A entidade Adotante de código %d não pode ser removida pois está em uso", id));
    }
}
