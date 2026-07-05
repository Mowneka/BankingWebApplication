package com.bank.BankingWebApplication.controller;

import com.bank.BankingWebApplication.model.User;
import com.bank.BankingWebApplication.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String profile(
            Authentication authentication,
            Model model) {

        String username = authentication.getName();

        User user = userRepository
                .findByUsername(username)
                .orElse(null);

        model.addAttribute("user", user);

        return "profile";
    }
}