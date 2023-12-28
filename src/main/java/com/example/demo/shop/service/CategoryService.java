package com.example.demo.shop.service;

import com.example.demo.shop.dto.CategoryDto;
import com.example.demo.shop.dto.ProductDto;
import com.example.demo.shop.mapping.CategoryMapper;
import com.example.demo.shop.mapping.ProductMapper;
import com.example.demo.shop.model.Category;
import com.example.demo.shop.model.Product;
import com.example.demo.shop.repository.CategoryRepository;
import com.example.demo.shop.repository.ProductRepository;
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

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category save = categoryRepository.save(categoryMapper.reverse(categoryDto));
        return categoryMapper.convert(save);
    }

    public List<ProductDto> getProductsByCategory(String name) {
        Category category = categoryRepository.findCategoryByName(name);
        List<Product> products = productRepository.findProductByCategory(category);
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product : products) {
            productsDto.add(productMapper.convert(product));
        }
        return productsDto;
    }
}
