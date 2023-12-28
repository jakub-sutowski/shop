package com.example.demo.shop.controller;

import com.example.demo.shop.dto.CategoryDto;
import com.example.demo.shop.dto.ProductDto;
import com.example.demo.shop.service.CategoryService;
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
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable("name") String name) {
        List<ProductDto> productsByCategory = categoryService.getProductsByCategory(name);
        return ResponseEntity.ok(productsByCategory);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto categoryDtoCreate = categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(categoryDtoCreate);
    }
}
