package com.splitwise.service;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.entity.Expense;



public interface SplitService {
    void createSplit(Expense expense, ExpenseRequestDTO expenseRequestDTO);

}
