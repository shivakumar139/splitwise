package com.splitwise.controller;

import com.splitwise.dto.response.ApiResponse;
import com.splitwise.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;
    @GetMapping("{userId}")
    public ResponseEntity<ApiResponse<Object>> getWalletById(@PathVariable String userId){
        return new ResponseEntity<>(walletService.getWalletById(userId), HttpStatus.OK);
    }
}
