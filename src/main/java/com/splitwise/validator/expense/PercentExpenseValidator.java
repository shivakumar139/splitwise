package com.splitwise.validator.expense;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PercentExpenseValidator implements ExpenseValidator{
    @Override
    public boolean validate(ExpenseRequestDTO expenseRequestDTO) {

        List<Double> shares = expenseRequestDTO.getParticipants().getShares();

        double totalShares = shares.stream().mapToDouble(Double::doubleValue).sum();

        // the no of ids and no of share should be equal

        return (100 == totalShares && expenseRequestDTO.getParticipants().getIds().size() == shares.size());
    }
}
