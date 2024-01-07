package com.example.shop.shop.model.request;

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
public class ProductRequest {

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Positive
    private double price;
    private String description;
    private String imageLink;
    private String category;
    private List<OpinionRequest> opinions;
}
