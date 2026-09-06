package com.umc.sistemaonganimal.domain.exception;

public class ResponsavelNotFoundException extends EntityNotFoundException {
    public ResponsavelNotFoundException(String message) {
        super(message);
    }

    public ResponsavelNotFoundException(Long id) {
        this(String.format(String.format("Não existe um registro de Responsável com o id: %d", id)));
    }
}
