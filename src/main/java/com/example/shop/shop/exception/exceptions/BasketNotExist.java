package com.example.shop.shop.exception.exceptions;

public class BasketNotExist extends RuntimeException {

    public BasketNotExist(String basketId) {
        super("Basket " + basketId + " not exist");
    }
}
