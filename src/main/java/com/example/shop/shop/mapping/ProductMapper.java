package com.example.shop.shop.mapping;

import com.example.shop.shop.dto.ProductDto;
import com.example.shop.shop.exception.exceptions.CategoryNotExist;
import com.example.shop.shop.model.Product;
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
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .imageLink(productDto.getImageLink())
                .category(categoryRepository.findCategoryByName(productDto.getCategory()).orElseThrow(() ->
                        new CategoryNotExist(productDto.getCategory())))
                .build();
    }

    public List<ProductDto> convertList(List<Product> products) {
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product : products) {
            productsDto.add(convert(product));
        }
        return productsDto;
    }
}
