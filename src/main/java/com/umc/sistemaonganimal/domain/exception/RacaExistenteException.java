package com.umc.sistemaonganimal.domain.exception;

public class RacaExistenteException extends EntityExistsException {
    public RacaExistenteException(String message) {
        super(message);
    }

    public RacaExistenteException(String nome, String especieNome) {
        this(String.format("Já existe uma raça chamada '%s' cadastrada para a espécie '%s'", nome, especieNome));
    }
}
