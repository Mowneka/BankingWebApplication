package com.bank.BankingWebApplication.controller;

import com.bank.BankingWebApplication.model.User;
import com.bank.BankingWebApplication.repository.LoanRepository;
import com.bank.BankingWebApplication.repository.TransactionRepository;
import com.bank.BankingWebApplication.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/admin")
    public String adminPage(Model model) {

        long totalUsers = userRepository.count();

        long totalTransactions =
                transactionRepository.count();

        long totalLoans =
                loanRepository.count();

        long totalDeposits =
                transactionRepository
                        .findByType("DEPOSIT")
                        .size();

        long totalWithdrawals =
                transactionRepository
                        .findByType("WITHDRAW")
                        .size();

        long totalTransfers =
                transactionRepository
                        .findByType("TRANSFER")
                        .size();

        double totalBalance = 0;

        for(User user : userRepository.findAll()) {

            totalBalance += user.getBalance();
        }

        model.addAttribute(
                "totalUsers",
                totalUsers);

        model.addAttribute(
                "totalTransactions",
                totalTransactions);

        model.addAttribute(
                "totalLoans",
                totalLoans);

        model.addAttribute(
                "totalDeposits",
                totalDeposits);

        model.addAttribute(
                "totalWithdrawals",
                totalWithdrawals);

        model.addAttribute(
                "totalTransfers",
                totalTransfers);

        model.addAttribute(
                "totalBalance",
                totalBalance);

        model.addAttribute(
                "users",
                userRepository.findAll());

        model.addAttribute(
                "transactions",
                transactionRepository.findAll());

        model.addAttribute(
                "loans",
                loanRepository.findAll());

        return "admin";
    }
}