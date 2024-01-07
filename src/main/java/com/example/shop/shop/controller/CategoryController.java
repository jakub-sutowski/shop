package com.example.shop.shop.controller;

import com.example.shop.shop.model.request.CategoryRequest;
import com.example.shop.shop.model.request.ProductRequest;
import com.example.shop.shop.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Log4j2
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{name}")
    public ResponseEntity<List<ProductRequest>> getProductsByCategory(@PathVariable("name") String name) {
        return ResponseEntity.ok(categoryService.getProductsByCategory(name));
    }

    @PostMapping
    public ResponseEntity<CategoryRequest> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        log.info("Basket {} successfully created", categoryRequest);
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }
}
