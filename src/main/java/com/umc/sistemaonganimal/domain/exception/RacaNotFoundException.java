package com.umc.sistemaonganimal.domain.exception;

public class RacaNotFoundException extends EntityNotFoundException {
    public RacaNotFoundException(String message) {
        super(message);
    }

    public RacaNotFoundException(Long id) {
        this(String.format(String.format("Não existe de uma raça com o id: %d", id)));
    }
}
