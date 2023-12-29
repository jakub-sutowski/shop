package com.example.shop.shop.mapping;

import com.example.shop.shop.dto.CategoryDto;
import com.example.shop.shop.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {
    @Override
    public CategoryDto convert(Category from) {
        return CategoryDto.builder()
                .name(from.getName())
                .build();
    }

    @Override
    public Category reverse(CategoryDto from) {
        return Category.builder()
                .name(from.getName())
                .build();
    }
}
