package com.splitwise.splitter;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.entity.User;
import com.splitwise.enums.ParticipantType;
import com.splitwise.service.GroupService;
import com.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class EqualAmountSplitter implements AmountSplitter{

    @Override
    public List<OwedDetails> split(ExpenseRequestDTO expenseRequestDTO) {

        double totalAmount = expenseRequestDTO.getAmount();
        int totalUsers = expenseRequestDTO.getParticipants().getIds().size();
        List<String> usersIds = expenseRequestDTO.getParticipants().getIds();


        double perShareAmount = totalAmount / totalUsers;
        perShareAmount = Math.round(perShareAmount * 100.00)/100.0;

        /**
         * In case total amount is 100 and totalUsers is 3 (33.34 + 33.33 + 33.33)
         * divide the extra money to the users to shared amount should be
         * equal to the total amount
         */


        double remain = totalAmount - (perShareAmount * totalUsers);
        remain = Math.round(remain * 100.00)/100.0;


        List<OwedDetails> owedDetails = new ArrayList<>();

        for(String id: usersIds){

            if(remain != 0){
                double newAmount = perShareAmount + remain;
                remain = 0;
                newAmount = Math.round(newAmount * 100.00)/100.0;
                owedDetails.add(new OwedDetails(id, newAmount));
                continue;
            }


            owedDetails.add(new OwedDetails(id, perShareAmount));

        }

        return owedDetails;

    }
}
