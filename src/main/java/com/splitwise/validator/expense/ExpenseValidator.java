package com.splitwise.validator.expense;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;

public interface ExpenseValidator {
    boolean validate(ExpenseRequestDTO expenseRequestDTO);
}
