package com.splitwise.service;

import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Expense;

public interface DebtService {
    void addDebts(Expense expense);
    ApiResponse<Object> getDebtById(String userId);

}
