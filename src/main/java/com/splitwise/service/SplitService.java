package com.splitwise.service;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.entity.Expense;
import com.splitwise.enums.ExpenseType;
import com.splitwise.enums.ParticipantType;

import java.util.Set;
import java.util.UUID;


public interface SplitService {
    void createSplit(Expense expense, ExpenseRequestDTO expenseRequestDTO);

}
