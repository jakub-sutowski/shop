package com.example.shop.shop.exception.exceptions;

public class UserNotExist extends RuntimeException {

    public UserNotExist(String email) {

        super("User with email" + email + " not exist");
    }
}
