package com.splitwise.validator.expense;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExactExpenseValidator implements ExpenseValidator{
    @Override
    public boolean validate(ExpenseRequestDTO expenseRequestDTO) {
        double totalAmount = expenseRequestDTO.getAmount();

        List<Double> sharedAmount = expenseRequestDTO.getParticipants().getShares();

        double totalSharedAmount = sharedAmount.stream().mapToDouble(Double::doubleValue).sum();

        // the no of ids and no of share should be equal

        return (totalAmount == totalSharedAmount && expenseRequestDTO.getParticipants().getIds().size() == sharedAmount.size());
    }
}
