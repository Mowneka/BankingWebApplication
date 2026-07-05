package com.bank.BankingWebApplication.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.BankingWebApplication.model.Otp;
import com.bank.BankingWebApplication.repository.OtpRepository;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    public String generateOtp(String username) {

        Random random = new Random();

        String otp =
                String.valueOf(
                        100000 + random.nextInt(900000)
                );

        Otp otpEntity =
                otpRepository
                        .findByUsername(username)
                        .orElse(new Otp());

        otpEntity.setUsername(username);

        otpEntity.setOtpCode(otp);

        otpRepository.save(otpEntity);

        return otp;
    }

    public boolean validateOtp(
            String username,
            String otpCode) {

        Otp otp =
                otpRepository
                        .findByUsername(username)
                        .orElse(null);

        if (otp == null) {

            return false;
        }

        return otp.getOtpCode()
                .equals(otpCode);
    }
}