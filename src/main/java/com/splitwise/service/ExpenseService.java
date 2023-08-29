package com.splitwise.service;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.request.expense.GroupExpenseRequestDTO;
import com.splitwise.dto.request.expense.UserExpenseRequestDTO;

public interface ExpenseService {
    String createExpenseByUsers(UserExpenseRequestDTO userExpenseRequestDTO);
    String createExpenseByGroup(GroupExpenseRequestDTO userExpenseRequestDTO);
    String createExpenseIndividual(ExpenseRequestDTO expenseRequestDTO);
}
