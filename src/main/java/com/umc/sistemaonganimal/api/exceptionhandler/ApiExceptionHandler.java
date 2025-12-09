package com.umc.sistemaonganimal.api.exceptionhandler;

import com.sun.net.httpserver.HttpsServer;
import com.umc.sistemaonganimal.domain.exception.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?>  handleEntityNotFoundException(EntityNotFoundException e){
        ApiError apiError = ApiError.builder()
                .data(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(){
        ApiError apiError = ApiError.builder()
                .data(LocalDateTime.now())
                .mensagem("Requisição inválida: verifique se todos os campos obrigatórios foram preenchidos corretamente.")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}

