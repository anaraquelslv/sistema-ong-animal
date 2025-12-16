package com.umc.sistemaonganimal.domain.exception;

public abstract class EntityNotFoundException extends DomainException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
