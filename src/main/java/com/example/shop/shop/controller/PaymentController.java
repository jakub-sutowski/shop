package com.example.shop.shop.controller;

import com.example.shop.shop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Log4j2
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<String> processPayment(Principal principal) {
        String statusPay = paymentService.pay(principal);
        return ResponseEntity.ok(statusPay);
    }
}
