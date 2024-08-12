package com.gestion.clientes.infrastructure.validator;

public class ChileRutValidator implements CedulaValidatorByCountry {
    @Override
    public boolean isValid(String cedula) {
        return cedula.matches("\\d{1,8}-[\\dkK]");
    }
}
