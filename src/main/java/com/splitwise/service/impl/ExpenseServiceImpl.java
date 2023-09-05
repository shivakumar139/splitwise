package com.splitwise.service.impl;


import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.request.expense.GroupExpenseRequestDTO;
import com.splitwise.dto.request.expense.UserExpenseRequestDTO;
import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.Expense;
import com.splitwise.entity.User;
import com.splitwise.enums.ExpenseCategory;
import com.splitwise.enums.ExpenseType;
import com.splitwise.exception.UserNotFound;
import com.splitwise.repository.ExpenseRepository;
import com.splitwise.repository.UserRepository;
import com.splitwise.service.ExpenseService;
import com.splitwise.service.UserService;
import com.sun.jdi.InternalException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    UserService userService;


    @Override
    public ApiResponse<Object> createExpenseWithUsers(UserExpenseRequestDTO userExpenseRequestDTO) {
        return null;
    }

    @Override
    public ApiResponse<Object> createExpense(ExpenseRequestDTO expenseRequestDTO) {
        return ApiResponse.builder()
                .success(true)
                .data(expenseRequestDTO)
                .message("Expense is created")
                .build();
    }

    @Override
    public ApiResponse<Object> createExpenseWithGroups(GroupExpenseRequestDTO userExpenseRequestDTO) {
       return null;
    }

    /**
     * Creates a new individual expense record based on the provided ExpenseRequestDTO.
     *
     * @param expenseRequestDTO The DTO containing information about the expense.
     * @return An ApiResponse containing a success message and the created expense ID.
     * @throws ConstraintViolationException if there are constraints violated during database operations.
     */
    @Transactional
    @Override
    public ApiResponse<Object> createExpenseIndividual(ExpenseRequestDTO expenseRequestDTO) {

        // check expense type individual
        if(expenseRequestDTO.getExpenseType() != ExpenseType.INDIVIDUAL) throw new DataIntegrityViolationException("Expense Type must be INDIVIDUAL");

        User payerUser = (User) userService.findUserById(expenseRequestDTO.getPayerId()).getData();
        String desc = expenseRequestDTO.getDesc();

        Expense expense = Expense.builder()
                .amount(expenseRequestDTO.getAmount())
                .category(expenseRequestDTO.getCategory())
                .expenseType(expenseRequestDTO.getExpenseType())
                .description(desc == null || desc.isEmpty() || desc.isBlank() ? "NA": desc)
                .payer(payerUser)
                .createdAt(LocalDateTime.now())
                .build();


        try{
            expense = expenseRepository.save(expense);

            return ApiResponse.builder()
                    .message("Expense Created")
                    .success(true)
                    .data(Map.of("ExpenseId", expense.getId()))
                    .build();

        }catch(ConstraintViolationException e){
            System.out.println(e.getMessage());
            throw e;
        }

    }
}
