package com.example.demo.shop.controller;

import com.example.demo.shop.dto.BasketDto;
import com.example.demo.shop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @PostMapping("/{productId}/{userId}")
    public ResponseEntity<BasketDto> addProductToBasket(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId) {
        BasketDto basketDto = basketService.addProductToBasket(userId, productId);
        return ResponseEntity.ok(basketDto);
    }

}
