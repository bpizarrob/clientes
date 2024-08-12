package com.gestion.clientes.infrastructure.validator;

import com.gestion.clientes.domain.Customer;
import com.gestion.clientes.domain.exception.EmptyCustomerException;
import com.gestion.clientes.domain.exception.IdValidationException;
import com.gestion.clientes.domain.exception.InvalidCedulaException;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IdValidatorTest {
    @InjectMocks
    private IdValidator idValidator;

    @Mock
    private CedulaValidatorByCountryFactory cedulaValidatorByCountryFactory;

    @Mock
    private CedulaValidatorByCountry cedulaValidator;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsValid_ValidCedula() {
        Customer customer = new Customer();
        customer.setPais("Chile"); // Suponiendo que "EC" es un país válido
        customer.setCedula("17363919-8");

        when(cedulaValidatorByCountryFactory.getValidator("Chile")).thenReturn(cedulaValidator);
        when(cedulaValidator.isValid("17363919-8")).thenReturn(true);

        boolean result = idValidator.isValid(customer, constraintValidatorContext);

        assertTrue(result);
        verify(cedulaValidatorByCountryFactory).getValidator("Chile");
        verify(cedulaValidator).isValid("17363919-8");
    }

    @Test
    public void testIsValid_NullCustomer() {
        assertThrows(EmptyCustomerException.class, () -> {
            idValidator.isValid(null, constraintValidatorContext);
        });
    }

    @Test
    public void testIsValid_NullCedula() {
        Customer customer = new Customer();
        customer.setPais("Chile");
        customer.setCedula(null);

        assertThrows(IdValidationException.class, () -> {
            idValidator.isValid(customer, constraintValidatorContext);
        });
    }

    @Test
    public void testIsValid_InvalidCedula() {
        Customer customer = new Customer();
        customer.setPais("Chile");
        customer.setCedula("1234567890");

        when(cedulaValidatorByCountryFactory.getValidator("Chile")).thenReturn(cedulaValidator);
        when(cedulaValidator.isValid("1234567890")).thenReturn(false);

        assertThrows(InvalidCedulaException.class, () -> {
            idValidator.isValid(customer, constraintValidatorContext);
        });
    }

    @Test
    public void testValidarCedula_CountryNotSupported() {
        assertThrows(IdValidationException.class, () -> {
            idValidator.validarCedula("Peru", "1234567890");
        });
    }
}