package com.example.shop.shop.exception.exceptions;

public class TokenRequestException extends RuntimeException {


    public TokenRequestException(String email) {
        super("Token generation failed for user: " + email);
    }
}
