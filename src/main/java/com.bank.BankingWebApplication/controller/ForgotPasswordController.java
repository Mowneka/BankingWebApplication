package com.bank.BankingWebApplication.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bank.BankingWebApplication.model.User;
import com.bank.BankingWebApplication.repository.UserRepository;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String generatedOtp;
    private String currentUsername;

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/generate-otp")
    public String generateOtp(
            @RequestParam String username,
            Model model) {

        User user = userRepository
                .findByUsername(username)
                .orElse(null);

        if(user == null) {

            model.addAttribute(
                    "message",
                    "Username not found");

            return "forgot-password";
        }

        currentUsername = username;

        generatedOtp =
                String.valueOf(
                        100000 +
                        new Random().nextInt(900000));

        model.addAttribute(
                "otp",
                generatedOtp);

        return "verify-otp";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestParam String otp,
            Model model) {

        if(otp.equals(generatedOtp)) {

            model.addAttribute(
                    "username",
                    currentUsername);

            return "reset-password";
        }

        model.addAttribute(
                "message",
                "Invalid OTP");

        model.addAttribute(
                "otp",
                generatedOtp);

        return "verify-otp";
    }

    @PostMapping("/reset-password")
    public String resetPassword(

            @RequestParam String username,

            @RequestParam String password,

            Model model) {

        User user =
                userRepository
                        .findByUsername(username)
                        .orElse(null);

        if(user != null) {

            user.setPassword(
                    passwordEncoder
                            .encode(password));

            userRepository.save(user);
        }

        model.addAttribute(
                "message",
                "Password Reset Successfully");

        return "login";
    }
}