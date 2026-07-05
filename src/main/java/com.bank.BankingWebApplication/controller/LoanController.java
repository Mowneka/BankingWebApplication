package com.bank.BankingWebApplication.controller;

import com.bank.BankingWebApplication.model.Loan;
import com.bank.BankingWebApplication.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/loan")
    public String loanPage(Model model) {

        model.addAttribute("loan", new Loan());

        return "loan";
    }

    @PostMapping("/applyLoan")
    public String applyLoan(
            @ModelAttribute Loan loan,
            Model model
    ) {

        double P = loan.getLoanAmount();

        double annualRate = 8.5;

        double R = annualRate / 12 / 100;

        int N = 60;

        double EMI =
                (P * R * Math.pow(1 + R, N)) /
                (Math.pow(1 + R, N) - 1);

        loan.setEmi(EMI);

        loanRepository.save(loan);

        model.addAttribute("emi", EMI);

        return "loan";
    }
}