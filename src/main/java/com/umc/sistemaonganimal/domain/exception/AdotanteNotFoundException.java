package com.umc.sistemaonganimal.domain.exception;

public class AdotanteNotFoundException extends EntityNotFoundException {
    public AdotanteNotFoundException(String message) {
        super(message);
    }

    public AdotanteNotFoundException(Long id) {
        this(String.format(String.format("Não existe um registro de Adotante com o id: %d", id)));
    }
}
