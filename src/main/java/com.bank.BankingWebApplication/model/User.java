package com.bank.BankingWebApplication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role;

    private double balance;

    private String email;

    private String accountNumber;

private String aadhaarNumber;

private String mobileNumber;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
public String getAccountNumber() {
    return accountNumber;
}

public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
}

public String getAadhaarNumber() {
    return aadhaarNumber;
}

public void setAadhaarNumber(String aadhaarNumber) {
    this.aadhaarNumber = aadhaarNumber;
}

public String getMobileNumber() {
    return mobileNumber;
}

public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
}
}
