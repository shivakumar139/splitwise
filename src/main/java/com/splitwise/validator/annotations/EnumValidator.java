package com.splitwise.validator.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class EnumValidator implements ConstraintValidator<NotBlankEnum, Object> {
    private Object enumClass;

    @Override
    public void initialize(NotBlankEnum annotation) {
        enumClass = annotation.enumClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Null or empty values are not valid
        }

        return enumClass.equals(value.getClass());
    }
}
