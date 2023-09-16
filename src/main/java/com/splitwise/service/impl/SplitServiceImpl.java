package com.splitwise.service.impl;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.entity.Expense;
import com.splitwise.entity.Split;
import com.splitwise.enums.ParticipantType;
import com.splitwise.factory.AmountSplitterFactory;
import com.splitwise.repository.SplitRepository;
import com.splitwise.service.SplitService;
import com.splitwise.service.UserService;
import com.splitwise.splitter.AmountSplitter;
import com.splitwise.splitter.OwedDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class SplitServiceImpl implements SplitService {

    @Autowired
    SplitRepository splitRepository;

    @Autowired
    UserService userService;

    @Autowired
    AmountSplitterFactory amountSplitterFactory;

    @Override
    public List<Split> createSplit(Expense expense, ExpenseRequestDTO expenseRequestDTO) {
        // get amountSplitter based on expense type
        AmountSplitter amountSplitter = amountSplitterFactory.getObject(expenseRequestDTO.getExpenseType());

        List<OwedDetails> owedDetailsList = amountSplitter.split(expenseRequestDTO);

        List<Split> splits = new ArrayList<>();

        if(expenseRequestDTO.getParticipants().getType() == ParticipantType.USERS){
            splits = splitWithUsers(expense, owedDetailsList);
        }


        return splits;
    }


    private List<Split> splitWithUsers(Expense expense, List<OwedDetails> owedDetailsList ) {

        return owedDetailsList.stream().map(owedDetails ->
                Split.builder()
                        .user(owedDetails.getUser())
                        .amount(owedDetails.getShare())
                        .expense(expense)
                        .build()
        ).toList();


    }
}
