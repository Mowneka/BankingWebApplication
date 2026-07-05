package com.bank.BankingWebApplication.controller;

import com.bank.BankingWebApplication.model.User;
import com.bank.BankingWebApplication.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
@PostMapping("/register")
public String registerUser(@ModelAttribute User user) {

    user.setPassword(
            passwordEncoder.encode(user.getPassword())
    );

    if (user.getRole() == null || user.getRole().isEmpty()) {
        user.setRole("USER");
    }

    // Generate Account Number
   String accountNumber =
        "SBK" +
        String.format("%011d",
        System.currentTimeMillis() % 100000000000L);

user.setAccountNumber(accountNumber);

    user.setAccountNumber(accountNumber);

    userRepository.save(user);

    return "redirect:/login";
}
    }
