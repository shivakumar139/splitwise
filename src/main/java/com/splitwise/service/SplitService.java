package com.splitwise.service;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.entity.Expense;
import com.splitwise.entity.Split;

import java.util.List;


public interface SplitService {
    List<Split> createSplit(Expense expense, ExpenseRequestDTO expenseRequestDTO);

}
