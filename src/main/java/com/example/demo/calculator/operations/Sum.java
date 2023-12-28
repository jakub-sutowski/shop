package com.example.demo.calculator.operations;

import com.example.demo.calculator.Operation;
import org.springframework.stereotype.Service;

@Service
public class Sum implements Operation {

    @Override
    public double calculate(double a, double b) {
        return a + b;
    }

    @Override
    public String getSymbol() {
        return "+";
    }
}
