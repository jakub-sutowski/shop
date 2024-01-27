package com.example.shop.shop.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDto {

    @NotNull
    @Size(min = 2)
    private String name;
    @NotNull
    private Long productCode;
    @NotNull
    @Positive
    private double price;
    private String description;
    private String imageLink;
    private String category;
    private List<OpinionDto> opinions;
}
