package com.example.shop.shop.controller;

import com.example.shop.shop.dto.BasketDto;
import com.example.shop.shop.service.BasketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
@Log4j2
public class BasketController {
    private final BasketService basketService;

    @PostMapping("/{productId}/{userId}")
    public ResponseEntity<BasketDto> addProductToBasket(
            @Valid
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId) {
        BasketDto basketDto = basketService.addProductToBasket(userId, productId);
        log.info("Basket {} successfully created" ,basketDto);
        return ResponseEntity.ok(basketDto);
    }

}
