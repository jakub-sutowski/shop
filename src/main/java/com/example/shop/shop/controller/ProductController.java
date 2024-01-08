package com.example.shop.shop.controller;

import com.example.shop.shop.model.request.OpinionRequest;
import com.example.shop.shop.model.request.ProductRequest;
import com.example.shop.shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productCode}")
    public ResponseEntity<ProductRequest> getProduct(@PathVariable("productCode") Long productCode) {
        ProductRequest productRequests = productService.getProduct(productCode);
        return ResponseEntity.ok(productRequests); // zwracamy docelowy produkt
    }

    @GetMapping("/{productCode}/opinions")
    public ResponseEntity<Page<OpinionRequest>> getOpinionsByProduct(@PathVariable("productCode") Long productCode,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Page<OpinionRequest> opinionsByProduct = productService.getOpinionsByProduct(productCode, page, size);
        return ResponseEntity.ok(opinionsByProduct);
    }

    @PostMapping
    public ResponseEntity<ProductRequest> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductRequest productRequest1 = productService.createProduct(productRequest);
        log.info("Product {} successfully created", productRequest1);
        return ResponseEntity.ok(productRequest1); // tworze produkt
    }

    // zadania na pozniej - zmienic konstrukcje aplikacji tzn. pobierajac produkt
    // nie pobieramy opini tylko dodajemy osobny endpoitn do pobierania opinii. Sciezka get/product/id/opinions
}
