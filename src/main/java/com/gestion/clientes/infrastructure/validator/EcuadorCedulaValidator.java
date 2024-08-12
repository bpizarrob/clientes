package com.gestion.clientes.infrastructure.validator;

public class EcuadorCedulaValidator implements CedulaValidatorByCountry {
    @Override
    public boolean isValid(String cedula) {
        return cedula.matches("\\d{10}");
    }
}
