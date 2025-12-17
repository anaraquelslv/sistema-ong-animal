package com.umc.sistemaonganimal.api.exceptionhandler;

import com.sun.net.httpserver.HttpsServer;
import com.umc.sistemaonganimal.domain.exception.DomainException;
import com.umc.sistemaonganimal.domain.exception.EntityInUseException;
import com.umc.sistemaonganimal.domain.exception.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.Field;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String detail = "Verifique se os campos estão preenchidos corretamente";

        ProblemDetail problemDetail = createProblemDetail(status, detail, "Violação de restrição", "https://pivic-ong/erro-restricao");

        Map<String, String> fields = ex.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage()));

        problemDetail.setProperty("detalhes", fields);

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    private ProblemDetail createProblemDetail(HttpStatusCode status, String detail, String title, String type) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(title);
        problem.setType(URI.create(type));

        return problem;
    }
}

