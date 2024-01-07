package com.example.shop.shop.mapping;

import com.example.shop.shop.exception.exceptions.CategoryNotExist;
import com.example.shop.shop.model.entity.Product;
import com.example.shop.shop.model.request.ProductRequest;
import com.example.shop.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper implements Mapper<Product, ProductRequest> {

    private final CategoryRepository categoryRepository;

    @Override
    public ProductRequest convert(Product product) {
        return ProductRequest.builder()
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .imageLink(product.getImageLink())
                .category(product.getCategory().getName())
                .build();
    }

    @Override
    public Product reverse(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .imageLink(productRequest.getImageLink())
                .category(categoryRepository.findCategoryByName(productRequest.getCategory()).orElseThrow(() ->
                        new CategoryNotExist(productRequest.getCategory())))
                .build();
    }

    public List<ProductRequest> convertList(List<Product> products) {
        List<ProductRequest> productRequests = new ArrayList<>();
        for (Product product : products) {
            productRequests.add(convert(product));
        }
        return productRequests;
    }
}
