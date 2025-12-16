package com.umc.sistemaonganimal.domain.exception;

public class EspecieNotFoundException extends EntityNotFoundException {
    public EspecieNotFoundException(String message) {
        super(message);
    }

    public EspecieNotFoundException(Long id) {
        this(String.format(String.format("Não existe um registro de Espécie com o id: %d", id)));
    }
}
