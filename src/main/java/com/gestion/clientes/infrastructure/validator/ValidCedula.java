package com.gestion.clientes.infrastructure.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdValidator.class)
@Documented
public @interface ValidCedula {
    String message() default "Cédula inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
