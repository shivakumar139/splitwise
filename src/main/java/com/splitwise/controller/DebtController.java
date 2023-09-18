package com.splitwise.controller;

import com.splitwise.dto.response.ApiResponse;
import com.splitwise.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/debts")
public class DebtController {
    @Autowired
    private DebtService debtService;

    @GetMapping("{userId}")
    public ResponseEntity<ApiResponse<Object>> getDebtById(@PathVariable String userId){
        return new ResponseEntity<>(debtService.getDebtById(userId), HttpStatus.OK);
    }
}
