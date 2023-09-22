package com.splitwise.controller;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
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


    @GetMapping("{userId}")
    public ResponseEntity<ApiResponse<Object>> getAllExpenseByUserId(@PathVariable String userId, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber){

        return new ResponseEntity<>(expenseService.getAllExpenseByUserId(userId, pageSize, pageNumber), HttpStatus.CREATED);
    }

}
