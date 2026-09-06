package com.umc.sistemaonganimal.domain.exception;

public abstract class EntityExistsException extends DomainException {
    public EntityExistsException(String message) {
        super(message);
    }
}
