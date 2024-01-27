package com.example.shop.shop.controller;

import com.example.shop.shop.model.dto.BasketDto;
import com.example.shop.shop.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/basket")
@RequiredArgsConstructor
@Log4j2
public class BasketController {

    private final BasketService basketService;

    @Operation(
            summary = "Add product to basket",
            description = "Adds a product to the user's basket."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product successfully added to the basket"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    @PostMapping("/{productCode}")
    public ResponseEntity<BasketDto> addProductToBasket(
            @Valid
            @PathVariable("productCode") Long productCode,
            Principal principal) {
        BasketDto basketDto = basketService.addProductToBasket(principal, productCode);
        log.info("Basket {} successfully created", basketDto);
        return ResponseEntity.ok(basketDto);
    }

}
