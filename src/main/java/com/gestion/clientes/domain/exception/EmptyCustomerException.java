package com.gestion.clientes.domain.exception;

public class EmptyCustomerException extends RuntimeException{
    private String code;

    public EmptyCustomerException(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }
}
