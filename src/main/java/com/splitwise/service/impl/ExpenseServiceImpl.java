package com.splitwise.service.impl;


import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.request.expense.GroupExpenseRequestDTO;
import com.splitwise.dto.request.expense.UserExpenseRequestDTO;
import com.splitwise.entity.Expense;
import com.splitwise.entity.User;
import com.splitwise.exception.UserNotFound;
import com.splitwise.repository.ExpenseRepository;
import com.splitwise.repository.UserRepository;
import com.splitwise.service.ExpenseService;
import com.sun.jdi.InternalException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public String createExpenseByUsers(UserExpenseRequestDTO userExpenseRequestDTO) {
        System.out.println(userExpenseRequestDTO);
        return "success";
    }

    @Override
    public String createExpenseByGroup(GroupExpenseRequestDTO groupExpenseRequestDTO) {
        System.out.println(groupExpenseRequestDTO);
        return "success";
    }

    @Transactional
    @Override
    public String createExpenseIndividual(ExpenseRequestDTO expenseRequestDTO) {
        User payerUser = userRepository.findById(expenseRequestDTO.getPayerId()).orElseThrow(()-> new UserNotFound("User with id: " + expenseRequestDTO.getPayerId() + " Not Exist"));

        try{
            Expense expense = Expense.builder()
                    .amount(expenseRequestDTO.getAmount())
                    .category(expenseRequestDTO.getCategory())
                    .expenseType(expenseRequestDTO.getExpenseType())
                    .description(expenseRequestDTO.getDesc())
                    .payer(payerUser)
                    .build();
            System.out.println(expense);

            expenseRepository.save(expense);
        }catch(Exception e){
            throw new InternalException(e.getMessage());
        }
        return null;
    }
}
