package com.splitwise.splitter;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.entity.User;
import com.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PercentAmountSplitter implements AmountSplitter{

    @Autowired
    private UserService userService;

    @Override
    public List<OwedDetails> split(ExpenseRequestDTO expenseRequestDTO) {
        List<String> ids = expenseRequestDTO.getParticipants().getIds().stream().toList();
        List<Double> shares = expenseRequestDTO.getParticipants().getShares();
        double totalAmount = expenseRequestDTO.getAmount();

        List<OwedDetails> owedDetails = new ArrayList<>();

        for(int i=0; i< shares.size(); i++){
            User user = (User) userService.findUserById(ids.get(i)).getData();
            double percentAmount = getPercentageAmount(totalAmount, shares.get(i));

            owedDetails.add(new OwedDetails(user, percentAmount));
        }

        return owedDetails;
    }

    private Double getPercentageAmount(Double total, Double percent) {
        return (total*percent)/100.0;
    }
}
