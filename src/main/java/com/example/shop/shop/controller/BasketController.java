package com.example.shop.shop.controller;

import com.example.shop.shop.model.request.BasketRequest;
import com.example.shop.shop.service.BasketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
@Log4j2
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/{productId}")
    public ResponseEntity<BasketRequest> addProductToBasket(
            @Valid
            @PathVariable("productId") Long productId,
            Principal principal) {
        BasketRequest basketRequest = basketService.addProductToBasket(principal, productId);
        log.info("Basket {} successfully created", basketRequest);
        return ResponseEntity.ok(basketRequest);
    }

}
