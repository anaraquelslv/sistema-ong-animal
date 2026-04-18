package com.umc.sistemaonganimal.domain.exception;

public abstract class EntityInUseException extends DomainException {
    public EntityInUseException(String message) {
        super(message);
    }
}
