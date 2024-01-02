package com.example.shop.shop.controller;

import com.example.shop.shop.dto.request.OpinionRequest;
import com.example.shop.shop.dto.request.ProductRequest;
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
    public ResponseEntity<ProductRequest> getProduct(@PathVariable("id") Long id) {
        ProductRequest productRequests = productService.getProduct(id);
        return ResponseEntity.ok(productRequests); // zwracamy docelowy produkt
    }

    @GetMapping("/{id}/opinions")
    public ResponseEntity<List<OpinionRequest>> getOpinionsByProduct(@PathVariable("id") Long id) {
        List<OpinionRequest> opinionsByProduct = productService.getOpinionsByProduct(id);
        return ResponseEntity.ok(opinionsByProduct);
    }

    @PostMapping
    public ResponseEntity<ProductRequest> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductRequest productRequest1 = productService.createProduct(productRequest);
        return ResponseEntity.ok(productRequest1); // tworze produkt
    }

    // zadania na pozniej - zmienic konstrukcje aplikacji tzn. pobierajac produkt
    // nie pobieramy opini tylko dodajemy osobny endpoitn do pobierania opinii. Sciezka get/product/id/opinions
}
