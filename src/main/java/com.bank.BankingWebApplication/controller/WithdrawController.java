package com.bank.BankingWebApplication.controller;

import java.time.LocalDateTime;

import com.bank.BankingWebApplication.model.Transaction;
import com.bank.BankingWebApplication.model.User;
import com.bank.BankingWebApplication.repository.TransactionRepository;
import com.bank.BankingWebApplication.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WithdrawController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/withdraw")
    public String withdrawPage() {
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdrawMoney(
            @RequestParam double amount,
            Authentication authentication,
            Model model) {

        String username = authentication.getName();

        User user = userRepository
                .findByUsername(username)
                .orElse(null);

        if (user != null) {

            if (user.getBalance() < amount) {

                model.addAttribute(
                        "message",
                        "Insufficient Balance");

            } else {

                user.setBalance(user.getBalance() - amount);

                userRepository.save(user);

                Transaction tx = new Transaction();

                tx.setSender(username);
                tx.setReceiver("SELF");
                tx.setType("WITHDRAW");
                tx.setAmount(amount);
                tx.setTransactionDate(LocalDateTime.now());

                transactionRepository.save(tx);

                model.addAttribute(
                        "message",
                        "₹" + amount
                                + " withdrawn successfully. Remaining Balance: ₹"
                                + user.getBalance());
            }
        }

        return "withdraw";
    }
}