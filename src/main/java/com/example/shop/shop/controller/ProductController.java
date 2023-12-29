package com.example.shop.shop.controller;

import com.example.shop.shop.dto.OpinionDto;
import com.example.shop.shop.dto.ProductDto;
import com.example.shop.shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
        ProductDto productDto = productService.getProduct(id);
        return ResponseEntity.ok(productDto); // zwracamy docelowy produkt
    }

    @GetMapping("/{id}/opinions")
    public ResponseEntity<List<OpinionDto>> getOpinionsByProduct(@PathVariable("id") Long id) {
        List<OpinionDto> opinionsByProduct = productService.getOpinionsByProduct(id);
        return ResponseEntity.ok(opinionsByProduct);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto productDtoCreate = productService.createProduct(productDto);
        return ResponseEntity.ok(productDtoCreate); // tworze produkt
    }

    // zadania na pozniej - zmienic konstrukcje aplikacji tzn. pobierajac produkt
    // nie pobieramy opini tylko dodajemy osobny endpoitn do pobierania opinii. Sciezka get/product/id/opinions
}
