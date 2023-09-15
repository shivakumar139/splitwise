package com.splitwise.service.impl;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Expense;
import com.splitwise.entity.Split;
import com.splitwise.entity.User;
import com.splitwise.enums.ExpenseType;
import com.splitwise.enums.ParticipantType;
import com.splitwise.factory.AmountSplitterFactory;
import com.splitwise.repository.SplitRepository;
import com.splitwise.service.SplitService;
import com.splitwise.service.UserService;
import com.splitwise.splitter.AmountSplitter;
import com.splitwise.splitter.OwedDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class SplitServiceImpl implements SplitService {

    @Autowired
    SplitRepository splitRepository;

    @Autowired
    UserService userService;

    @Autowired
    AmountSplitterFactory amountSplitterFactory;

    @Override
    public void createSplit(Expense expense, ExpenseRequestDTO expenseRequestDTO) {
        // get amountSplitter based on expense type
        AmountSplitter amountSplitter = amountSplitterFactory.getObject(expenseRequestDTO.getExpenseType());

        List<OwedDetails> owedDetailsList = amountSplitter.split(expenseRequestDTO);


        if(expenseRequestDTO.getParticipants().getType() == ParticipantType.USERS){
            saveSplitWithUsers(expense, owedDetailsList);
        }

    }


    private void saveSplitWithUsers(Expense expense, List<OwedDetails> owedDetailsList ) {

        List<Split> splits = owedDetailsList.stream().map(owedDetails ->
                Split.builder()
                        .user(owedDetails.getUser())
                        .amount(owedDetails.getShare())
                        .expense(expense)
                        .build()
        ).toList();


        splitRepository.saveAll(splits);


    }
}
