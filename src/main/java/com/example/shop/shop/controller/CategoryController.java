package com.example.shop.shop.controller;

import com.example.shop.shop.dto.request.CategoryRequest;
import com.example.shop.shop.dto.request.ProductRequest;
import com.example.shop.shop.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{name}")
    public ResponseEntity<List<ProductRequest>> getProductsByCategory(@PathVariable("name") String name) {
        return ResponseEntity.ok(categoryService.getProductsByCategory(name));
    }

    @PostMapping
    public ResponseEntity<CategoryRequest> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }
}
