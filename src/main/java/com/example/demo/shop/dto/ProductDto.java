package com.example.demo.shop.dto;

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
    private String name;
    private double price;
    private String description;
    private String imageLink;
    private String category;
    private List<OpinionDto> opinions;
}
