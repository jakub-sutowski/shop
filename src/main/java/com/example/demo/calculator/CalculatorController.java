package com.example.demo.calculator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/calculator")
public class CalculatorController {
    /*
     obiekt Calculator ( liczba a, liczba b, dzialanie)
     serwis
        strona glowna - formularz
        strona - wynik
        kontrolore do wyzej (endpointy)

     */

    private final CalculatorServices calculatorServices;

    @GetMapping
    public String home(ModelMap map) {
        Calculator calculator = new Calculator();
        map.addAttribute("calculatorForm", calculator);
        map.addAttribute("operators", calculatorServices.getAllOperators());
        return "calculator";
    }

    @PostMapping
    public String calculate(ModelMap map, @ModelAttribute("calculatorForm") Calculator calculatorForm) {
        double result = calculatorServices.calculate(calculatorForm);
        map.addAttribute("result", result);
        return "result-calc";
    }
}
