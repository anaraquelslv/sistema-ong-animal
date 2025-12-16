package com.umc.sistemaonganimal.api.exceptionhandler;

import com.sun.net.httpserver.HttpsServer;
import com.umc.sistemaonganimal.domain.exception.DomainException;
import com.umc.sistemaonganimal.domain.exception.EntityInUseException;
import com.umc.sistemaonganimal.domain.exception.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFoundException(EntityNotFoundException ex) {
        return createProblemDetail(HttpStatus.NOT_FOUND, ex.getMessage(), "Entidade não encontrada", "https://pivic-ong/entidade-nao-encontrada");
    }

    @ExceptionHandler(EntityInUseException.class)
    public ProblemDetail handleEntityInUseException(EntityInUseException ex) {
        return createProblemDetail(HttpStatus.CONFLICT, ex.getMessage(),"Entidade em uso", "https://pivic-ong/entidade-em-uso" );
    }

    @ExceptionHandler(DomainException.class)
    public ProblemDetail handleDomainException(DomainException ex) {
        return createProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage(), "Violação de regra de negócio", "https://pivic-ong/erro-domain");
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String detail, String title, String type) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(title);
        problem.setType(URI.create(type));

        return problem;
    }
}

