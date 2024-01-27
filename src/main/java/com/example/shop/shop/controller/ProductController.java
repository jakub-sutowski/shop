package com.example.shop.shop.controller;

import com.example.shop.shop.model.dto.OpinionDto;
import com.example.shop.shop.model.dto.ProductDto;
import com.example.shop.shop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Get product details",
            description = "Retrieves details of a specific product."
    )
    @GetMapping("/{productCode}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("productCode") Long productCode) {
        ProductDto productRequests = productService.getProduct(productCode);
        return ResponseEntity.ok(productRequests);
    }

    @Operation(
            summary = "Get opinions by product",
            description = "Retrieves opinions for a specific product."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Opinions successfully retrieved"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    @GetMapping("/{productCode}/opinions")
    public ResponseEntity<Page<OpinionDto>> getOpinionsByProduct(@PathVariable("productCode") Long productCode,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Page<OpinionDto> opinionsByProduct = productService.getOpinionsByProduct(productCode, page, size);
        return ResponseEntity.ok(opinionsByProduct);
    }

    @Operation(
            summary = "Create product",
            description = "Creates a new category with the provided details."
    )
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto productDto1 = productService.createProduct(productDto);
        log.info("Product {} successfully created", productDto1);
        return ResponseEntity.ok(productDto1); // tworze produkt
    }
}
