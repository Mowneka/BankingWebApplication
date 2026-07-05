package com.bank.BankingWebApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bank.BankingWebApplication.model.Transaction;
import com.bank.BankingWebApplication.repository.TransactionRepository;

@Controller
public class HistoryController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/history")
    public String history(
            Authentication authentication,
            Model model) {

        String username = authentication.getName();

        List<Transaction> transactions =
                transactionRepository
                        .findBySenderOrReceiverOrderByTransactionDateDesc(
                                username,
                                username
                        );

        model.addAttribute(
                "transactions",
                transactions
        );

        return "history";
    }
}