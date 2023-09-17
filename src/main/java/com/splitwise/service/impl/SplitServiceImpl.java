package com.splitwise.service.impl;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.request.expense.Participants;
import com.splitwise.entity.Expense;
import com.splitwise.entity.Group;
import com.splitwise.entity.Split;
import com.splitwise.entity.User;
import com.splitwise.enums.ExpenseType;
import com.splitwise.enums.ParticipantType;
import com.splitwise.factory.AmountSplitterFactory;
import com.splitwise.repository.SplitRepository;
import com.splitwise.service.GroupService;
import com.splitwise.service.SplitService;
import com.splitwise.service.UserService;
import com.splitwise.splitter.AmountSplitter;
import com.splitwise.splitter.OwedDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class SplitServiceImpl implements SplitService {

    @Autowired
    SplitRepository splitRepository;

    @Autowired
    UserService userService;

    @Autowired
    AmountSplitterFactory amountSplitterFactory;

    @Autowired
    private GroupService groupService;

    @Override
    public List<Split> createSplit(Expense expense, ExpenseRequestDTO expenseRequestDTO) {
        // get amountSplitter based on expense type
        AmountSplitter amountSplitter = amountSplitterFactory.getObject(expenseRequestDTO.getExpenseType());

        List<OwedDetails> owedDetailsList = amountSplitter.split(expenseRequestDTO);

        List<Split> splits = new ArrayList<>();

        if(expenseRequestDTO.getParticipants().getType() == ParticipantType.USERS){
            splits = splitWithUsers(expense, owedDetailsList);
        }

        if(expenseRequestDTO.getParticipants().getType() == ParticipantType.GROUPS){
            splits = splitWithGroups(expense, owedDetailsList, expenseRequestDTO);
        }


        return splits;
    }

    private List<Split> splitWithGroups(Expense expense, List<OwedDetails> owedDetailsList, ExpenseRequestDTO expenseRequestDTO) {

        List<Split> splits = new ArrayList<>();

        owedDetailsList.forEach(owedDetail -> {
            Group group = groupService.findById(owedDetail.getId());
            Set<User> users = group.getUsers();
            List<String> ids = users.stream().map(User::getId).toList();

            // get equal amountSplitter because money is equally divided between group users
            AmountSplitter amountSplitter = amountSplitterFactory.getObject(ExpenseType.EQUAL);


            Participants participants = Participants.builder()
                    .shares(expenseRequestDTO.getParticipants().getShares())
                    .ids(ids)
                    .build();


            ExpenseRequestDTO expenseRequestDTO1 = ExpenseRequestDTO.builder()
                    .amount(owedDetail.getShare())
                    .participants(participants)
                    .build();
            List<OwedDetails> owedDetailsList1 = amountSplitter.split(expenseRequestDTO1);

            splits.addAll(splitWithUsers(expense, owedDetailsList1));

        });

        return splits;

    }


    private List<Split> splitWithUsers(Expense expense, List<OwedDetails> owedDetailsList ) {

        return owedDetailsList.stream().map(owedDetails ->
                Split.builder()
                        .user((User)userService.findUserById(owedDetails.getId()).getData())
                        .amount(owedDetails.getShare())
                        .expense(expense)
                        .build()

        ).toList();


    }
}
