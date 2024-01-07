package com.example.shop.shop.validation;

import com.example.shop.shop.exception.exceptions.CategoryAlreadyExist;
import com.example.shop.shop.model.entity.Category;
import com.example.shop.shop.model.request.CategoryRequest;
import com.example.shop.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryValidator {

    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryRequest categoryDto) {
        Optional<Category> categoryByName = categoryRepository.findCategoryByName(categoryDto.getName());
        if (categoryByName.isPresent()) {
            throw new CategoryAlreadyExist(categoryDto.getName());
        }
    }
}
