package com.splitwise.validator.annotations;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.enums.ExpenseType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class ExpenseTypeValidator implements ConstraintValidator<ValidExpenseType, ExpenseRequestDTO> {
    @Override
    public boolean isValid(ExpenseRequestDTO expenseRequestDTO, ConstraintValidatorContext context) {
        // Check if expenseType is INDIVIDUAL and participants is null
        if (expenseRequestDTO.getExpenseType() == ExpenseType.INDIVIDUAL) {
            if (expenseRequestDTO.getParticipants() != null) {
                context.buildConstraintViolationWithTemplate("Participants is not needed in INDIVIDUAL Expense").addConstraintViolation();
                context.disableDefaultConstraintViolation();
                return false;
            }
            return true; // Skip validation in this case
        }



        // If not INDIVIDUAL or participants is not null, validate participants field.
        if (expenseRequestDTO.getParticipants() == null) {
            context.buildConstraintViolationWithTemplate("Participants are missing").addConstraintViolation();
            return false;
        }
        return true;
    }
}
