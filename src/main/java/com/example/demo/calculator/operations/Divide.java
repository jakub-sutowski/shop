package com.example.demo.calculator.operations;

import com.example.demo.calculator.Operation;
import org.springframework.stereotype.Service;

@Service
public class Divide implements Operation {
    @Override
    public double calculate(double a, double b) {
        if (b == 0) {
            return 0;
        }
        return a / b;
    }

    @Override
    public String getSymbol() {
        return "/";
    }
}
