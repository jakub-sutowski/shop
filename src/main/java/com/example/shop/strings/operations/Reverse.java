package com.example.shop.strings.operations;

import com.example.shop.strings.StringOperation;
import org.springframework.stereotype.Service;

@Service
public class Reverse implements StringOperation {

    @Override
    public String execute(String text) {
        return new StringBuilder(text).reverse().toString();
    }

    @Override
    public String getAlgorithm() {
        return "Reverse";
    }
}
