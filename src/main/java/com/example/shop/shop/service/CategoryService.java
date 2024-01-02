package com.example.shop.shop.service;

import com.example.shop.shop.dto.request.CategoryRequest;
import com.example.shop.shop.dto.request.ProductRequest;
import com.example.shop.shop.exception.exceptions.CategoryNotExist;
import com.example.shop.shop.mapping.CategoryMapper;
import com.example.shop.shop.mapping.ProductMapper;
import com.example.shop.shop.model.Category;
import com.example.shop.shop.model.Product;
import com.example.shop.shop.repository.CategoryRepository;
import com.example.shop.shop.repository.ProductRepository;
import com.example.shop.shop.validation.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;
    private final CategoryValidator categoryValidator;

    public CategoryRequest createCategory(CategoryRequest categoryRequest) {
        categoryValidator.createCategory(categoryRequest);
        Category save = categoryRepository.save(categoryMapper.reverse(categoryRequest));
        return categoryMapper.convert(save);
    }

    public List<ProductRequest> getProductsByCategory(String name) {
        Category category = categoryRepository.findCategoryByName(name).orElseThrow(() -> new CategoryNotExist(name));;
        List<Product> products = productRepository.findProductByCategory(category);
        List<ProductRequest> productRequests = new ArrayList<>();
        for (Product product : products) {
            productRequests.add(productMapper.convert(product));
        }
        return productRequests;
    }
}
