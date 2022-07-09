package com.example.spring.validations;

import com.example.spring.annotations.PasswordValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {

    private List<String> weakPasswords;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        weakPasswords  = List.of("123456", "password", "qwerty");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !weakPasswords.contains(s);
    }

}
