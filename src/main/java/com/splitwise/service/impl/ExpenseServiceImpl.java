package com.splitwise.service.impl;


import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Expense;
import com.splitwise.entity.Group;
import com.splitwise.entity.Split;
import com.splitwise.entity.User;
import com.splitwise.enums.ExpenseType;
import com.splitwise.enums.ParticipantType;
import com.splitwise.exception.InvalidExpenseException;
import com.splitwise.factory.ExpenseValidatorFactory;
import com.splitwise.repository.ExpenseRepository;
import com.splitwise.service.ExpenseService;
import com.splitwise.service.GroupService;
import com.splitwise.service.SplitService;
import com.splitwise.service.UserService;
import com.splitwise.validator.expense.ExpenseValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    UserService userService;

    @Autowired
    SplitService splitService;

    @Autowired
    ExpenseValidatorFactory expenseValidatorFactory;

    @Autowired
    private GroupService groupService;



    @Transactional
    @Override
    public ApiResponse<Object> createExpense(ExpenseRequestDTO expenseRequestDTO) {

        if(expenseRequestDTO.getExpenseType() == ExpenseType.INDIVIDUAL){
            return createIndividualExpense(expenseRequestDTO);
        }
        ExpenseValidator expenseValidator = expenseValidatorFactory.getObject(expenseRequestDTO.getExpenseType());

        if(!expenseValidator.validate(expenseRequestDTO)){
            throw new InvalidExpenseException("Invalid Expense");

        }

        User payer = (User) userService.findUserById(expenseRequestDTO.getPayerId()).getData();



        Expense expense = Expense.builder()
                .payer(payer)
                .expenseType(expenseRequestDTO.getExpenseType())
                .category(expenseRequestDTO.getCategory())
                .description(expenseRequestDTO.getDesc())
                .amount(expenseRequestDTO.getAmount())
                .build();

        List<Split> splits = splitService.createSplit(expense, expenseRequestDTO);

        expense.setSplits(splits);

        if(expenseRequestDTO.getParticipants().getType() == ParticipantType.GROUPS){
            List<String> ids = expenseRequestDTO.getParticipants().getIds();
            List<Group> groups = ids.stream().map(id -> groupService.findById(id)).toList();
            expense.setGroups(groups);
        }


        expense = expenseRepository.save(expense);

        return ApiResponse.builder()
                .success(true)
                .data(expense)
                .message("Expense is created")
                .build();
    }




    private ApiResponse<Object> createIndividualExpense(ExpenseRequestDTO expenseRequestDTO) {

        User payer = (User) userService.findUserById(expenseRequestDTO.getPayerId()).getData();

        Expense expense = Expense.builder()
                .amount(expenseRequestDTO.getAmount())
                .category(expenseRequestDTO.getCategory())
                .expenseType(expenseRequestDTO.getExpenseType())
                .description(expenseRequestDTO.getDesc())
                .payer(payer)
                .build();


        expense = expenseRepository.save(expense);

        return ApiResponse.builder()
                .message("Expense Created")
                .success(true)
                .data(expense)
                .build();

    }

    @Override
    public ApiResponse<Object> getAllExpense() {
        List<Expense> expenses = expenseRepository.findAll();

        return ApiResponse.builder()
                .data(expenses)
                .message("All expense")
                .success(true)
                .build();
    }
}
