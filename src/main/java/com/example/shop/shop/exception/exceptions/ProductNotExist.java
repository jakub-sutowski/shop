package com.example.shop.shop.exception.exceptions;

public class ProductNotExist extends RuntimeException {
    public ProductNotExist(String productId) {
        super("Product " + productId + " not exist");
    }
}
