package com.splitwise.validator.annotations;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.enums.ExpenseType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.security.core.Authentication;


public class ExpenseTypeValidator implements ConstraintValidator<ValidExpenseType, ExpenseRequestDTO> {
    @Override
    public boolean isValid(ExpenseRequestDTO expenseRequestDTO, ConstraintValidatorContext context) {


        // Check if expenseType is INDIVIDUAL and participants is null
        if (expenseRequestDTO.getExpenseType() == ExpenseType.INDIVIDUAL) {
            if (expenseRequestDTO.getParticipants() != null) {
                context.buildConstraintViolationWithTemplate("Participants are not needed in INDIVIDUAL Expense").addConstraintViolation();
                context.disableDefaultConstraintViolation();
                return false;
            }
            return true; // Skip validation in this case
        }



        // if expense type is EQUAL shares are not needed
        if (expenseRequestDTO.getExpenseType() == ExpenseType.EQUAL){
            if (expenseRequestDTO.getParticipants().getShares() != null) {
                context.buildConstraintViolationWithTemplate("Shares are not needed in EQUAL Expense").addConstraintViolation();
                context.disableDefaultConstraintViolation();
                return false;
            }
            return true;
        }





        // If not INDIVIDUAL or participants is not null, validate participants field.
        if (expenseRequestDTO.getParticipants() == null) {
            context.buildConstraintViolationWithTemplate("Participants are missing").addConstraintViolation();
            return false;
        }
        if (expenseRequestDTO.getParticipants().getShares() == null) {
            context.buildConstraintViolationWithTemplate("shares are missing").addConstraintViolation();
            return false;
        }


        return true;
    }
}
