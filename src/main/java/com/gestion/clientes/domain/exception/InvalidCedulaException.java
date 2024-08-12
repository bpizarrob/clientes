package com.gestion.clientes.domain.exception;

public class InvalidCedulaException extends RuntimeException {
    public InvalidCedulaException(String message) {
        super(message);
    }

    public InvalidCedulaException(String message, Throwable cause) {
        super(message, cause);
    }
}
