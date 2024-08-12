package com.gestion.clientes.infrastructure.validator;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CedulaValidatorByCountryFactory {

    private static final Map<String, CedulaValidatorByCountry> validators = new HashMap<>();

    static {
        validators.put("Venezuela", new VenezuelaCedulaValidator());
        validators.put("USA", new UsaSsnValidator());
        validators.put("Ecuador", new EcuadorCedulaValidator());
        validators.put("Chile", new ChileRutValidator());
    }

    public CedulaValidatorByCountry getValidator(String pais) {
        return validators.get(pais);
    }
}
