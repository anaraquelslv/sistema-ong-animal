package com.umc.sistemaonganimal.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class EntityInUseException extends DomainException {
    public EntityInUseException(String message) {
        super(message);
    }
}
