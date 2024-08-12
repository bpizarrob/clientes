package com.gestion.clientes.infrastructure.validator;

public class VenezuelaCedulaValidator implements CedulaValidatorByCountry {
    @Override
    public boolean isValid(String cedula) {
        return cedula.matches("\\d{8,9}");
    }
}
