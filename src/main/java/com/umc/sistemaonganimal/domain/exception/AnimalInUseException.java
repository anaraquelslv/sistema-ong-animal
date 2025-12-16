package com.umc.sistemaonganimal.domain.exception;

public class AnimalInUseException extends EntityInUseException {
    public AnimalInUseException(String message) {
        super(message);
    }

    public AnimalInUseException(Long id) {
        this(String.format("A entidade Animal de código %d não pode ser removida pois está em uso", id));
    }
}
