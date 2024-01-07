package com.example.shop.shop.exception.exceptions;

public class CategoryAlreadyExist extends RuntimeException {
    public CategoryAlreadyExist(String categoryName) {
        super("Category " + categoryName + " already exist ");
    }
}
