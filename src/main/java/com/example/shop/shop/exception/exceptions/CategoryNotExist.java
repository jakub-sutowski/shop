package com.example.shop.shop.exception.exceptions;

public class CategoryNotExist extends RuntimeException {

    public CategoryNotExist(String categoryName) {
        super(categoryName + " not exist");
    }
}
