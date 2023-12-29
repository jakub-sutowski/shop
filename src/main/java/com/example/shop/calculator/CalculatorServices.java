package com.example.shop.calculator;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CalculatorServices {
    private final Map<String, Operation> operationMap;

    public CalculatorServices(Set<Operation> allOperations) {
        operationMap = allOperations.stream().collect(Collectors.toMap(Operation::getSymbol, Function.identity()));
//        operationMap = new HashMap<>();
//        for (Operation allOperation : allOperations) {
//            operationMap.put(allOperation.getSymbol(), allOperation);
//        }
    }

    public double calculate(Calculator calculatorForm) {
        String operator = calculatorForm.getOperator();
        double a = calculatorForm.getA();
        double b = calculatorForm.getB();
        return operationMap.get(operator).calculate(a, b);
    }

    public Set<String> getAllOperators() {
        return operationMap.keySet();
    }
}
