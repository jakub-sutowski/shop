package com.example.shop.shop.mapping;

import com.example.shop.shop.exception.exceptions.CategoryNotExist;
import com.example.shop.shop.model.entity.Product;
import com.example.shop.shop.model.dto.ProductDto;
import com.example.shop.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper implements Mapper<Product, ProductDto> {

    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto convert(Product product) {
        return ProductDto.builder()
                .productCode(product.getProductCode())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .imageLink(product.getImageLink())
                .category(product.getCategory().getName())
                .build();
    }

    @Override
    public Product reverse(ProductDto productDto) {
        return Product.builder()
                .productCode(productDto.getProductCode())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .imageLink(productDto.getImageLink())
                .category(categoryRepository.findCategoryByName(productDto.getCategory()).orElseThrow(() ->
                        new CategoryNotExist(productDto.getCategory())))
                .build();
    }

    public List<ProductDto> convertList(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(convert(product));
        }
        return productDtos;
    }
}
