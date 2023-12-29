package com.example.shop.strings;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/strings")
public class StringControler {
    private final StringServices stringServices;

    @GetMapping
    public String home(ModelMap map) {
        StringForm stringForm = new StringForm();
        map.addAttribute("stringForm", stringForm);
        map.addAttribute("algorithms", stringServices.getAllAlgorithm());
        return "strings";
    }

    @PostMapping
    public String execute(ModelMap map, @ModelAttribute("stringForm") StringForm stringForm) {
        String result = stringServices.execute(stringForm);
        map.addAttribute("result", result);
        return "result-strings";
    }
}
