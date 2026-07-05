package com.bank.BankingWebApplication.controller;

import java.util.List;

import com.bank.BankingWebApplication.model.Transaction;
import com.bank.BankingWebApplication.model.User;
import com.bank.BankingWebApplication.repository.TransactionRepository;
import com.bank.BankingWebApplication.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {

        String username = authentication.getName();

        User user = userRepository
                .findByUsername(username)
                .orElse(null);

        if (user == null) {
            return "redirect:/login";
        }

        List<Transaction> transactions =
                transactionRepository
                        .findBySenderOrderByTransactionDateDesc(username);

        long deposits = transactionRepository
                .findByType("DEPOSIT")
                .size();

        long withdrawals = transactionRepository
                .findByType("WITHDRAW")
                .size();

        long transfers = transactionRepository
                .findByType("TRANSFER")
                .size();

        model.addAttribute("user", user);
        model.addAttribute("transactions", transactions);
        model.addAttribute("deposits", deposits);
        model.addAttribute("withdrawals", withdrawals);
        model.addAttribute("transfers", transfers);

        return "dashboard";
    }
}