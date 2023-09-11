package com.splitwise.splitter;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.entity.User;
import com.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ExactAmountSplitter implements AmountSplitter{

    @Autowired
    private UserService userService;



    @Override
    public List<OwedDetails> split(ExpenseRequestDTO expenseRequestDTO) {

        System.out.println("User Service ->" + userService);
        List<String> userIds = expenseRequestDTO.getParticipants().getIds().stream().toList();
        List<Double> shares = expenseRequestDTO.getParticipants().getShares();

        List<OwedDetails> owedDetails = new ArrayList<>();

        // get one user and create a OwedDetail with that user
        for(int i=0; i<shares.size(); i++){
            User user = (User) userService.findUserById(userIds.get(i)).getData();

            System.out.println("After UserService");
            OwedDetails owedDetail = OwedDetails.builder()
                    .user(user)
                    .share(shares.get(i))
                    .build();

            owedDetails.add(owedDetail);
        }

        return owedDetails;
    }
}
