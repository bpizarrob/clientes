package com.gestion.clientes.infrastructure.validator;

import java.util.regex.Pattern;

public class UsaSsnValidator implements CedulaValidatorByCountry {
    private static final Pattern SSN_PATTERN = Pattern.compile("\\d{3}-\\d{2}-\\d{4}");

    @Override
    public boolean isValid(String ssn) {
        if (ssn == null || !SSN_PATTERN.matcher(ssn).matches()) {
            return false;
        }

        return !isReserved(ssn) && !isNotAllowed(ssn);
    }

    private boolean isReserved(String ssn) {
        return ssn.startsWith("000") || ssn.startsWith("666") || ssn.startsWith("9");
    }

    private boolean isNotAllowed(String ssn) {
        // Nos permitiria agregar validaciones adicionales en caso de requerirlas
        return false;
    }
}
