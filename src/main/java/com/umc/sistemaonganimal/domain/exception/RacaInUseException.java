package com.umc.sistemaonganimal.domain.exception;

public class RacaInUseException extends EntityInUseException {
    public RacaInUseException(String message) {
        super(message);
    }

    public RacaInUseException(Long id) {
        this(String.format("A entidade Raça de código %d não pode ser removida pois está em uso", id));
    }
}
