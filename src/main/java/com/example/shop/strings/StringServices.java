package com.example.shop.strings;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StringServices {
    private final Map<String, StringOperation> operationMap;

    public StringServices(Set<StringOperation> allOperations) {
        operationMap = allOperations.stream().collect(Collectors.toMap(StringOperation::getAlgorithm, Function.identity()));
    }

    public String execute(StringForm stringForm) {
        String algorithm = stringForm.getAlgorithm();
        String text = stringForm.getText();
        return operationMap.get(algorithm).execute(text);
    }

    public Set<String> getAllAlgorithm() {
        return operationMap.keySet();
    }
}
