package com.splitwise.splitter;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;

import java.util.List;

public interface AmountSplitter {
    List<OwedDetails> split(ExpenseRequestDTO expenseRequestDTO);
}
