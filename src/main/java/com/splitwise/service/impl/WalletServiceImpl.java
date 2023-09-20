package com.splitwise.service.impl;

import com.splitwise.dto.response.ApiResponse;
import com.splitwise.entity.*;
import com.splitwise.mapper.CustomMapper;
import com.splitwise.repository.WalletRepository;
import com.splitwise.service.DebtService;
import com.splitwise.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private DebtService debtService;

    @Autowired
    private CustomMapper customMapper;


    @Override
    public void createOrUpdateWallet(Expense expense) {
        List<Split> splits = expense.getSplits();
        User payer = expense.getPayer();

        if(!walletRepository.existsByUser(payer)){

            createWallet(payer);
        }

        for(Split split: splits){
            User payee = split.getUser();

            if(!walletRepository.existsByUser(payee)){
                createWallet(payee);
            }

            // if payer and payee both are same do nothing
            if(payer.getId().equals(payee.getId())) continue;



            if(!debtService.existsByPayerAndPayee(payee, payer)){

                // means payee not own any money to payer
                // now payer own money and payee is payable
                Wallet payerWallet = walletRepository.findByUserId(payer.getId());
                payerWallet.setOwn(payerWallet.getOwn() + split.getAmount());

                Wallet payeeWallet = walletRepository.findByUserId(payee.getId());

                payeeWallet.setPayable(payeeWallet.getPayable() + split.getAmount());


            }
            else{
                Debt payeePayerDebt = debtService.findByPayerAndPayee(payee, payer);


                double newAmount = split.getAmount() - payeePayerDebt.getAmount();

                Wallet payeeWallet = walletRepository.findByUserId(payee.getId());
                Wallet payerWallet = walletRepository.findByUserId(payer.getId());

                System.out.println("New Amount " + newAmount);
                // if amount is positive update both user wallet payable and own
                if(newAmount > 0){

                    payerWallet.setOwn(payerWallet.getOwn() + newAmount);
                    payerWallet.setPayable(payerWallet.getPayable() - payeePayerDebt.getAmount());

                    payeeWallet.setPayable(payeeWallet.getPayable() + newAmount);
                    payeeWallet.setOwn(payeeWallet.getOwn() - payeePayerDebt.getAmount());
                }
                // if amount is negative payer is payable some amount and payee own new amount
                else if(newAmount < 0){
                    payerWallet.setPayable(payerWallet.getPayable()  + newAmount);
                    payeeWallet.setOwn(payeeWallet.getOwn() + newAmount);
                }
                // if amount is zero payer is now payable less amount and payee own less amount
                else{
                    payerWallet.setPayable(payerWallet.getPayable() -  payeePayerDebt.getAmount());
                    payeeWallet.setOwn(payeeWallet.getOwn() - payeePayerDebt.getAmount());
                }
            }
        }

    }

    private void createWallet(User user) {
        Wallet wallet = Wallet.builder()
                .user(user)
                .own(0.00)
                .payable(0.00)
                .build();

        walletRepository.save(wallet);

    }

    @Override
    public ApiResponse<Object> getWalletById(String userId) {
        Wallet wallet = walletRepository.findByUserId(userId);
        return ApiResponse.builder()
                .success(true)
                .message("User Wallet")
                .data(customMapper.map(wallet))
                .build();
    }
}
