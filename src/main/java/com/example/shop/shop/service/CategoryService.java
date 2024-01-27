package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.CategoryNotExist;
import com.example.shop.shop.mapping.CategoryMapper;
import com.example.shop.shop.mapping.ProductMapper;
import com.example.shop.shop.model.entity.Category;
import com.example.shop.shop.model.entity.Product;
import com.example.shop.shop.model.dto.CategoryDto;
import com.example.shop.shop.model.dto.ProductDto;
import com.example.shop.shop.repository.CategoryRepository;
import com.example.shop.shop.repository.ProductRepository;
import com.example.shop.shop.validation.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;
    private final CategoryValidator categoryValidator;

    @Transactional
    public CategoryDto createCategory(CategoryDto request) {
        categoryValidator.createCategory(request);
        Category savedCategory = categoryRepository.save(categoryMapper.reverse(request));
        return categoryMapper.convert(savedCategory);
    }

    public Page<ProductDto> getProductsByCategory(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Category category = categoryRepository.findCategoryByName(name).orElseThrow(() -> new CategoryNotExist(name));
        Page<Product> products = productRepository.findProductByCategory(category, pageRequest);

        return products.map(productMapper::convert);
    }
}
