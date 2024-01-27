package com.example.shop.shop.controller;

import com.example.shop.shop.service.PaymentService;
import com.example.shop.shop.type.StatusCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Log4j2
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(
            summary = "Process payment",
            description = "Processes a payment for the authenticated user."
    )
    @PostMapping
    public ResponseEntity<StatusCode> processPayment(Principal principal) {
        StatusCode statusPay = paymentService.pay(principal);
        return ResponseEntity.ok(statusPay);
    }
}
