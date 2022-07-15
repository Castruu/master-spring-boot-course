package com.example.spring.annotations;


import com.example.spring.validations.PasswordStrengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {

    Class<?>[] groups() default {};
    String message() default "Please choose a strong password";
    Class<? extends Payload>[] payload() default {};

}
