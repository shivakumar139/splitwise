package com.splitwise.service.impl;


import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Expense;
import com.splitwise.entity.Group;
import com.splitwise.entity.Split;
import com.splitwise.entity.User;
import com.splitwise.enums.ExpenseType;
import com.splitwise.enums.ParticipantType;
import com.splitwise.enums.RoleEnum;
import com.splitwise.exception.InvalidExpenseException;
import com.splitwise.factory.ExpenseValidatorFactory;
import com.splitwise.mapper.CustomMapper;
import com.splitwise.repository.ExpenseRepository;
import com.splitwise.service.*;
import com.splitwise.validator.expense.ExpenseValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
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

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private DebtService debtService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private RoleService roleService;



    @Transactional
    @Override
    public ApiResponse<Object> createExpense(ExpenseRequestDTO expenseRequestDTO) {

        if(expenseRequestDTO.getExpenseType() == ExpenseType.INDIVIDUAL){
            return createIndividualExpense(expenseRequestDTO);
        }
        ExpenseValidator expenseValidator = expenseValidatorFactory.getInstance(expenseRequestDTO.getExpenseType());

        if(!expenseValidator.validate(expenseRequestDTO)){
            throw new InvalidExpenseException("Invalid Expense");

        }

        // has permission to add expense in the group or not
        if(expenseRequestDTO.getParticipants().getType() == ParticipantType.GROUPS && !hasPermission(expenseRequestDTO)) throw new AccessDeniedException("Access Denied to add expense in this group");

        User payer = (User) userService.findUserById(expenseRequestDTO.getPayerId()).getData();


        Expense expense = customMapper.map(expenseRequestDTO);
        expense.setPayer(payer);

        List<Split> splits = splitService.createSplit(expense, expenseRequestDTO);

        expense.setSplits(splits);

        if(expenseRequestDTO.getParticipants().getType() == ParticipantType.GROUPS){
            List<String> ids = expenseRequestDTO.getParticipants().getIds();
            List<Group> groups = ids.stream().map(id -> groupService.findById(id)).toList();
            expense.setGroups(groups);
        }



        // update wallet of all users
        walletService.createOrUpdateWallet(expense);

        // update the debts of all users
        debtService.addDebts(expense);

        expense = expenseRepository.save(expense);





        return ApiResponse.builder()
                .success(true)
                .data(customMapper.map(expense))
                .message("Expense is created")
                .build();
    }

    private boolean hasPermission(ExpenseRequestDTO expenseRequestDTO) {
        List<String> groupIds = expenseRequestDTO.getParticipants().getIds();

        for (String groupId: groupIds){
            if(!roleService.hasPermission(RoleEnum.ROLE_GROUP_USER.withObjectId(groupId))){
                return false;
            }
        }

        return true;

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
                .data(customMapper.map(expense))
                .build();

    }

    @Override
    public ApiResponse<Object> getAllExpenseByUserId(String userId, int pageSize, int pageNumber) {
        Pageable sortByDate = PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending());
        List<Expense> expenses = expenseRepository.findAllByPayerId(userId, sortByDate);

        return ApiResponse.builder()
                .data(customMapper.map(expenses))
                .message("All expense")
                .success(true)
                .build();
    }
}
