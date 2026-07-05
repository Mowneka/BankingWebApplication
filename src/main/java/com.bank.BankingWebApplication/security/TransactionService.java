package com.bank.BankingWebApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.BankingWebApplication.model.Transaction;
import com.bank.BankingWebApplication.model.User;
import com.bank.BankingWebApplication.repository.TransactionRepository;
import com.bank.BankingWebApplication.repository.UserRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public String transferMoney(
            String senderUsername,
            String receiverUsername,
            double amount) {

        User sender = userRepository
                .findByUsername(senderUsername)
                .orElse(null);

        User receiver = userRepository
                .findByUsername(receiverUsername)
                .orElse(null);

        if (sender == null || receiver == null) {

            return "User not found";
        }

        if (sender.getBalance() < amount) {

            return "Insufficient Balance";
        }

        sender.setBalance(
                sender.getBalance() - amount
        );

        receiver.setBalance(
                receiver.getBalance() + amount
        );

        userRepository.save(sender);

        userRepository.save(receiver);

        Transaction transaction =
                new Transaction();

        transaction.setSender(senderUsername);

        transaction.setReceiver(receiverUsername);

        transaction.setAmount(amount);

        transactionRepository.save(transaction);

        return "Transfer Successful";
    }

    public List<Transaction> getAllTransactions() {

        return transactionRepository.findAll();
    }
}