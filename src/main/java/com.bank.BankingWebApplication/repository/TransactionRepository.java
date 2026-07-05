package com.bank.BankingWebApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.BankingWebApplication.model.Transaction;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByType(String type);

    List<Transaction> findBySenderOrderByTransactionDateDesc(String sender);

    List<Transaction> findBySenderOrReceiverOrderByTransactionDateDesc(
            String sender,
            String receiver
    );
}