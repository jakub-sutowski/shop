package com.example.shop.shop.controller;

import com.example.shop.shop.service.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @Operation(
            summary = "Say hello",
            description = "Returns a greeting message."
    )
    @GetMapping
    public String sayHello(Principal principal) {
        return helloService.sayHello(principal);
    }
}
