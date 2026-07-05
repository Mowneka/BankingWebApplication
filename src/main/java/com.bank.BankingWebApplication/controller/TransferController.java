package com.bank.BankingWebApplication.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bank.BankingWebApplication.model.Transaction;
import com.bank.BankingWebApplication.model.User;
import com.bank.BankingWebApplication.repository.TransactionRepository;
import com.bank.BankingWebApplication.repository.UserRepository;

@Controller
public class TransferController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/transfer")
    public String transferPage() {
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transferMoney(

            @RequestParam String senderAccount,
            @RequestParam String receiverAccount,
            @RequestParam double amount,
            Model model) {

        User sender = userRepository
                .findByAccountNumber(senderAccount)
                .orElse(null);

        User receiver = userRepository
                .findByAccountNumber(receiverAccount)
                .orElse(null);

        if(sender == null || receiver == null) {

            model.addAttribute(
                    "success",
                    "Invalid Account Number"
            );

            return "transfer";
        }

        if(sender.getBalance() < amount) {

            model.addAttribute(
                    "success",
                    "Insufficient Balance"
            );

            return "transfer";
        }

        sender.setBalance(sender.getBalance() - amount);

        receiver.setBalance(receiver.getBalance() + amount);

        userRepository.save(sender);
        userRepository.save(receiver);

        Transaction transaction = new Transaction();

        transaction.setSender(sender.getUsername());
        transaction.setReceiver(receiver.getUsername());
        transaction.setType("TRANSFER");
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        model.addAttribute(
                "success",
                "₹" + amount + " transferred successfully"
        );

        return "transfer";
    }
}