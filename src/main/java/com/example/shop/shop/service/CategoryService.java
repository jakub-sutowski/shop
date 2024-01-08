package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.CategoryNotExist;
import com.example.shop.shop.mapping.CategoryMapper;
import com.example.shop.shop.mapping.ProductMapper;
import com.example.shop.shop.model.entity.Category;
import com.example.shop.shop.model.entity.Product;
import com.example.shop.shop.model.request.CategoryRequest;
import com.example.shop.shop.model.request.ProductRequest;
import com.example.shop.shop.repository.CategoryRepository;
import com.example.shop.shop.repository.ProductRepository;
import com.example.shop.shop.validation.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public CategoryRequest createCategory(CategoryRequest request) {
        categoryValidator.createCategory(request);
        Category savedCategory = categoryRepository.save(categoryMapper.reverse(request));
        return categoryMapper.convert(savedCategory);
    }

    public Page<ProductRequest> getProductsByCategory(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Category category = categoryRepository.findCategoryByName(name).orElseThrow(() -> new CategoryNotExist(name));
        Page<Product> products = productRepository.findProductByCategory(category, pageRequest);

        return products.map(productMapper::convert);
    }
}
