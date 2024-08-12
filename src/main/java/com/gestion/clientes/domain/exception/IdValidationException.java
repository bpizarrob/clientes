package com.gestion.clientes.domain.exception;

public class IdValidationException extends RuntimeException{
    private String code;

    public IdValidationException(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }
}
