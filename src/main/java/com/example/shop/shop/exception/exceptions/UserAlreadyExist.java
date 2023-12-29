package com.example.shop.shop.exception.exceptions;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(String email) {
        super("User already exist " + email);
    }
}
