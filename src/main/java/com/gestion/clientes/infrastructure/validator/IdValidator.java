package com.gestion.clientes.infrastructure.validator;

import com.gestion.clientes.domain.Customer;
import com.gestion.clientes.domain.exception.EmptyCustomerException;
import com.gestion.clientes.domain.exception.IdValidationException;
import com.gestion.clientes.domain.exception.InvalidCedulaException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdValidator implements ConstraintValidator<ValidCedula, Customer> {

    private final CedulaValidatorByCountryFactory cedulaValidatorByCountryFactory;

    public IdValidator(CedulaValidatorByCountryFactory cedulaValidatorByCountryFactory) {
        this.cedulaValidatorByCountryFactory = cedulaValidatorByCountryFactory;
    }

    @Override
    public void initialize(ValidCedula constraintAnnotation) {}

    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext context) {

        isCustomerNull(customer);
        String country = customer.getPais();
        String cedula = customer.getCedula();

        isCedulaNull(cedula);
        validarCedula(country, cedula);

        return true;
    }

    private boolean isCustomerNull(Customer customer) {
        if (customer == null)
            throw new EmptyCustomerException("Customer cannot be empty."); // se controla en un handler global.

        return true;
    }

    private boolean isCedulaNull(String cedula) {
        if (cedula == null)
            throw new IdValidationException("Cedula field cannot be empty."); // se controla en un handler global.

        return true;
    }

    public boolean validarCedula(String country, String cedula) {

        CedulaValidatorByCountry validator = cedulaValidatorByCountryFactory.getValidator(country);
        isCountrySupported(validator);

        boolean isValid = validator.isValid(cedula);

        if (!isValid) {
            throw new InvalidCedulaException("The provided ID number is not valid for the country.: " + country);
        }

        return true;
    }

    private void isCountrySupported(CedulaValidatorByCountry validator) {
        if (validator == null) {
            throw new IdValidationException("Country not supported.");
        }
    }
}
