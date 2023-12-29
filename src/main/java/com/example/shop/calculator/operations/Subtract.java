package com.example.shop.calculator.operations;

import com.example.shop.calculator.Operation;
import org.springframework.stereotype.Service;

@Service
public class Subtract implements Operation {
    @Override
    public double calculate(double a, double b) {
        return a - b;
    }

    @Override
    public String getSymbol() {
        return "-";
    }
}
