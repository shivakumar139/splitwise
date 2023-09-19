package com.splitwise.service;

import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Expense;

public interface WalletService {
    void createOrUpdateWallet(Expense expense);
    ApiResponse<Object> getWalletById(String userId);
}
