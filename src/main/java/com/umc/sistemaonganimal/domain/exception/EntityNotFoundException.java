package com.umc.sistemaonganimal.domain.exception;

// TODO Alterar esse tratamento de erro para utilizar Handler Global futuramente


public abstract class EntityNotFindException extends RuntimeException {
    public EntityNotFindException(String message) {
        super(message);
    }
}
