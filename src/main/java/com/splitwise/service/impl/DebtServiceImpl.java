package com.splitwise.service.impl;

import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Debt;
import com.splitwise.entity.Expense;
import com.splitwise.entity.Split;
import com.splitwise.entity.User;
import com.splitwise.mapper.CustomMapper;
import com.splitwise.repository.DebtRepository;
import com.splitwise.service.DebtService;
import com.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtServiceImpl implements DebtService {

    @Autowired
    private DebtRepository debtRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomMapper customMapper;


    @Override
    public void addDebts(Expense expense) {
        List<Split> splits = expense.getSplits();
        User payer = expense.getPayer();

        for(Split split: splits) {
            User payee = split.getUser();

            if(payee.equals(payer)) continue;

            // payer -> payee , payee -> payer not exists simply save the debt because it's a fresh record
            if(!debtRepository.existsByPayerAndPayee(payer, payee) && !debtRepository.existsByPayerAndPayee(payee, payer)){

                Debt debt = Debt.builder()
                        .payer(payer)
                        .payee(payee)
                        .amount(split.getAmount())
                        .build();
                debtRepository.saveAndFlush(debt);


            } else if(!debtRepository.existsByPayerAndPayee(payee, payer)){
                // if payee -> payer not exists means payee not owes any money to payer simply
                // get prev debt and add the amount

                Debt prevDebt = debtRepository.findByPayerAndPayee(payer, payee);

                double newAmount = prevDebt.getAmount() + split.getAmount();

                // round off the value into two decimal places
                newAmount = Math.round(newAmount * 100.00)/100.0;

                // it will update the record in the database
                prevDebt.setAmount(newAmount);


            } else{
                // means payee -> payer exists , payee owes payer
                // get the (payee -> payer) calculate new amount
                Debt prevDebt = debtRepository.findByPayerAndPayee(payee, payer);
                double newAmount =  split.getAmount() - prevDebt.getAmount();
                newAmount = Math.round(newAmount * 100.00)/100.0;
                // if new amount is positive
                if(newAmount > 0){
                    // now payer get money from payee
                    // delete prev record and add new record (payee -> payer = newAmount)

                    debtRepository.deleteByPayerAndPayee(payee, payer);

                    Debt debt = Debt.builder()
                            .payer(payee)
                            .payee(payer)
                            .amount(newAmount)
                            .build();

                    debtRepository.save(debt);

                } else if(newAmount < 0){
                    // if negative
                    // payer needs to pay more amount to payee
                    // get prev debt update new amount
                    prevDebt.setAmount(Math.abs(newAmount));
                } else{
                    // if zero delete that record because nobody owes any money, money is settled
                    debtRepository.deleteByPayerAndPayee(payee, payer);

                }


            }

        }
    }

    @Override
    public ApiResponse<Object> getDebtById(String userId) {
        User user = (User) userService.findUserById(userId).getData();
        List<Debt> debts = debtRepository.findByPayerOrPayee(user);
        if(debts.isEmpty()){
            return ApiResponse.builder()
                    .success(true)
                    .message("Not have any Debts")
                    .build();
        }
        return ApiResponse.builder()
                .success(true)
                .message("List of debts")
                .data(customMapper.mapToDebtDtoList(debts))
                .build();
    }
}
