package com.bank.BankingWebApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.BankingWebApplication.model.User;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByAccountNumber(String accountNumber);

    Optional<User> findByAccountNumberEndingWith(String digits);
}