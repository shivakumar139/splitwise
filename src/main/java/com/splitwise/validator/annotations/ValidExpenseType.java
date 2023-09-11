package com.splitwise.validator.annotations;

import com.splitwise.validator.expense.ExpenseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


/**
 * If expense type is INDIVIDUAL
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExpenseTypeValidator.class)
@Target(ElementType.TYPE)
public @interface ValidExpenseType {
    String message() default "Invalid expense type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
