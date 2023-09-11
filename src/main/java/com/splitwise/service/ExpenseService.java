package com.splitwise.service;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.request.expense.GroupExpenseRequestDTO;
import com.splitwise.dto.request.expense.UserExpenseRequestDTO;
import com.splitwise.dto.response.ApiResponse;


public interface ExpenseService {
    ApiResponse<Object> createExpenseWithUsers(UserExpenseRequestDTO userExpenseRequestDTO);
    ApiResponse<Object> createExpense(ExpenseRequestDTO expenseRequestDTO);
    ApiResponse<Object> createExpenseWithGroups(GroupExpenseRequestDTO userExpenseRequestDTO);
    ApiResponse<Object> createExpenseIndividual(ExpenseRequestDTO expenseRequestDTO);
    ApiResponse<Object> getAllExpense();

}
