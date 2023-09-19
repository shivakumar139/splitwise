package com.splitwise.service;

import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Debt;
import com.splitwise.entity.Expense;
import com.splitwise.entity.User;

public interface DebtService {
    void addDebts(Expense expense);
    ApiResponse<Object> getDebtById(String userId);
    boolean existsByPayerAndPayee(User payer, User payee);
    Debt findByPayerAndPayee(User payer, User payee);

}
