package com.example.shop.strings.operations;

import com.example.shop.strings.StringOperation;
import org.springframework.stereotype.Service;

@Service
public class Upper implements StringOperation {
    @Override
    public String execute(String text) {
        return text.toUpperCase();
    }

    @Override
    public String getAlgorithm() {
        return "Upper";
    }
}
