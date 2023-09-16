package com.splitwise.splitter;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShareAmountSplitter implements AmountSplitter{


    @Override
    public List<OwedDetails> split(ExpenseRequestDTO expenseRequestDTO) {
        List<String> ids = expenseRequestDTO.getParticipants().getIds().stream().toList();
        List<Double> shares = expenseRequestDTO.getParticipants().getShares();
        double totalAmount = expenseRequestDTO.getAmount();

        double totalShare = expenseRequestDTO.getParticipants().getShares().stream().mapToDouble(Double::intValue).sum();

        List<OwedDetails> owedDetails = new ArrayList<>();

        for(int i=0; i< shares.size(); i++){
            double shareAmount = getShareAmount(totalAmount, totalShare ,shares.get(i));

            // round off to two decimal places
            shareAmount = Math.round(shareAmount * 100.00)/100.0;

            owedDetails.add(new OwedDetails(ids.get(i), shareAmount));
        }

        return owedDetails;
    }


    private Double getShareAmount(Double totalAmount, double totalShare, Double userShare) {
        return (totalAmount / totalShare)*userShare;
    }
}
