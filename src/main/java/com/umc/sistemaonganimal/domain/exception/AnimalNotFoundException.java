package com.umc.sistemaonganimal.domain.exception;

public abstract class AnimalNotFoundException extends EntityNotFoundException {
    public AnimalNotFoundException(String message) {
        super(message);
    }

    public AnimalNotFoundException(Long id) {
        this(String.format(String.format("Não existe um registro de Animal com o id: %d", id)));
    }
}
