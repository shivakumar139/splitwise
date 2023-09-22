package com.splitwise.service;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.response.ApiResponse;


public interface ExpenseService {
    ApiResponse<Object> createExpense(ExpenseRequestDTO expenseRequestDTO);
    ApiResponse<Object> getAllExpenseByUserId(String userId, int pageSize, int pageNumber);

}
