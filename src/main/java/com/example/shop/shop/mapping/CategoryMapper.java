package com.example.shop.shop.mapping;

import com.example.shop.shop.model.entity.Category;
import com.example.shop.shop.model.request.CategoryRequest;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements Mapper<Category, CategoryRequest> {

    @Override
    public CategoryRequest convert(Category from) {
        return CategoryRequest.builder()
                .name(from.getName())
                .build();
    }

    @Override
    public Category reverse(CategoryRequest from) {
        return Category.builder()
                .name(from.getName())
                .build();
    }
}
