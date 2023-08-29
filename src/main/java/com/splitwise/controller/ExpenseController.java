package com.splitwise.controller;

import com.splitwise.dto.request.expense.ExpenseRequestDTO;
import com.splitwise.dto.request.expense.GroupExpenseRequestDTO;
import com.splitwise.dto.request.expense.UserExpenseRequestDTO;
import com.splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("me")
    public ResponseEntity<String> createExpenseByUsers(){
        return new ResponseEntity<>("me", HttpStatus.OK);
    }


    @PostMapping("users")
    public ResponseEntity<String> createExpenseByUsers(@RequestBody UserExpenseRequestDTO userExpenseRequestDTO){
        return new ResponseEntity<>(expenseService.createExpenseByUsers(userExpenseRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("group")
    public ResponseEntity<String> createExpenseByGroup(@RequestBody GroupExpenseRequestDTO groupExpenseRequestDTO){
        return new ResponseEntity<>(expenseService.createExpenseByGroup(groupExpenseRequestDTO), HttpStatus.CREATED);
    }

    /**
     *
     * @return String
     * add the expense individually
     */
    @PostMapping("individual")
    public ResponseEntity<String> createExpenseIndividual(@RequestBody ExpenseRequestDTO expenseRequestDTO){
        return new ResponseEntity<>(expenseService.createExpenseIndividual(expenseRequestDTO), HttpStatus.CREATED);
    }
}
