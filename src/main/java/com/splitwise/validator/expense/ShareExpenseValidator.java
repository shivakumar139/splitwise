package com.splitwise.validator.expense;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import org.springframework.stereotype.Component;


@Component
public class ShareExpenseValidator implements ExpenseValidator{
    @Override
    public boolean validate(ExpenseRequestDTO expenseRequestDTO) {
        // total shares size and total user id should be equal
        return (expenseRequestDTO.getParticipants().getIds().size() == expenseRequestDTO.getParticipants().getShares().size());
    }
}
