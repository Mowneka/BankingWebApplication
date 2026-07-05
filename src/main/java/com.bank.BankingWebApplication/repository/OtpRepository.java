package com.bank.BankingWebApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.BankingWebApplication.model.Otp;

public interface OtpRepository
        extends JpaRepository<Otp, Long> {

    Optional<Otp> findByUsername(String username);
}