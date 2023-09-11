package com.splitwise.validator.annotations;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.enums.ExpenseType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Map;


public class ExpenseTypeValidator implements ConstraintValidator<ValidExpenseType, ExpenseRequestDTO> {
    @Override
    public boolean isValid(ExpenseRequestDTO expenseRequestDTO, ConstraintValidatorContext context) {
        if (expenseRequestDTO.getExpenseType() == ExpenseType.INDIVIDUAL) {
            // Do not validate participants field.
            context.disableDefaultConstraintViolation();
            return true;
        }

        // Validate participants field.
        context.buildConstraintViolationWithTemplate("Participants are missing").addConstraintViolation();
        return expenseRequestDTO.getParticipants() != null;
    }
}
