package com.winestoreapp.controller;

import com.winestoreapp.transaction.TransactionTCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TCarController {
    private final TransactionTCarService transactionTCarService;

    @GetMapping("/tc")
    public void test(){
        transactionTCarService.readAndIncreaseCarPrice();
    }

    @GetMapping("/tcr")
    public void readOnlyTransaction(){
        transactionTCarService.readOnlyTransaction();
    }
}
