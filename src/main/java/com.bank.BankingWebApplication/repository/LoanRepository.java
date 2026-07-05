package com.bank.BankingWebApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.BankingWebApplication.model.Loan;

public interface LoanRepository
        extends JpaRepository<Loan, Long> {

    List<Loan> findByStatus(String status);

}