package com.splitwise.controller;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.request.expense.GroupExpenseRequestDTO;
import com.splitwise.dto.request.expense.UserExpenseRequestDTO;
import com.splitwise.dto.response.ApiResponse;

import com.splitwise.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;


    /**
     * common end point for all expense type
     * @param expenseRequestDTO gets the common Expense
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createExpense(@Valid @RequestBody ExpenseRequestDTO expenseRequestDTO){
        return new ResponseEntity<>(expenseService.createExpense(expenseRequestDTO), HttpStatus.CREATED);
    }



    @PostMapping("users")
    public ResponseEntity<ApiResponse<Object>> createExpenseWithUsers(@RequestBody UserExpenseRequestDTO userExpenseRequestDTO){
        return new ResponseEntity<>(expenseService.createExpenseWithUsers(userExpenseRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("group")
    public ResponseEntity<ApiResponse<Object>> createExpenseWithGroups(@RequestBody GroupExpenseRequestDTO groupExpenseRequestDTO){
        return new ResponseEntity<>(expenseService.createExpenseWithGroups(groupExpenseRequestDTO), HttpStatus.CREATED);
    }


    @PostMapping("individual")
    public ResponseEntity<ApiResponse<Object>> createExpenseIndividual(@Valid @RequestBody ExpenseRequestDTO expenseRequestDTO){

        return new ResponseEntity<>(expenseService.createExpenseIndividual(expenseRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllExpense(){

        return new ResponseEntity<>(expenseService.getAllExpense(), HttpStatus.CREATED);
    }

}
