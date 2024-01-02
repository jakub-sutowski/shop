package com.example.shop.shop.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BasketOrderRequest {

    private ProductRequest product;

    @Positive
    private int quantity;
}

