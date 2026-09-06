package com.umc.sistemaonganimal.domain.exception;

public class TipoNotFoundException extends EntityNotFoundException {
    public TipoNotFoundException(String message) {
        super(message);
    }

    public TipoNotFoundException(Long id) {
        this(String.format(String.format("Não existe um registro de Tipo com o id: %d", id)));
    }
}
