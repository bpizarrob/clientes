package com.gestion.clientes.infrastructure.exceptions;

import com.gestion.clientes.application.dto.ErrorDetails;
import com.gestion.clientes.domain.exception.EmptyCustomerException;
import com.gestion.clientes.domain.exception.IdValidationException;
import com.gestion.clientes.domain.exception.InvalidCedulaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String BAD_REQUEST_CODE = String.valueOf(HttpStatus.BAD_REQUEST.value());

    @ExceptionHandler(IdValidationException.class)
    public ResponseEntity<ErrorDetails> handleCedulaValidationException(IdValidationException ex, WebRequest request) {
        return buildResponse(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(EmptyCustomerException.class)
    public ResponseEntity<ErrorDetails> handleClienteVacioException(EmptyCustomerException ex) {
        return buildResponse(ex.getMessage(), null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDetails> handleCuerpoPeticionVacio(HttpMessageNotReadableException ex, WebRequest request) {
        return buildResponse("The request body cannot be empty.", request.getDescription(false));
    }

    @ExceptionHandler(InvalidCedulaException.class)
    public ResponseEntity<ErrorDetails> handleInvalidCedulaException(InvalidCedulaException ex, WebRequest request) {
        return buildResponse(ex.getMessage(), request.getDescription(false));
    }

    private ResponseEntity<ErrorDetails> buildResponse(String message, String details) {
        ErrorDetails errorDetails = new ErrorDetails.Builder()
                .timestamp(new Date())
                .code(BAD_REQUEST_CODE)
                .message(message)
                .details(details)
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
