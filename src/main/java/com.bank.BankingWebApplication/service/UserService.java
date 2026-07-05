package com.bank.BankingWebApplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.BankingWebApplication.model.User;
import com.bank.BankingWebApplication.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        user.setRole("USER");

        user.setBalance(10000);

        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {

        userRepository.save(user);
    }
}