package com.example.shop.shop.controller;

import com.example.shop.shop.model.dto.CategoryDto;
import com.example.shop.shop.model.dto.ProductDto;
import com.example.shop.shop.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Log4j2
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(
            summary = "Get products by category",
            description = "Retrieves a paginated list of products for a given category."
    )
    @GetMapping("/{name}")
    public ResponseEntity<Page<ProductDto>> getProductsByCategory(@PathVariable("name") String name,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(categoryService.getProductsByCategory(name, page, size));
    }

    @Operation(
            summary = "Create a new category",
            description = "Creates a new category with the provided details."
    )
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        log.info("Basket {} successfully created", categoryDto);
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }
}