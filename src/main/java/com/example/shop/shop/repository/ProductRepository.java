package com.example.shop.shop.repository;

import com.example.shop.shop.model.entity.Category;
import com.example.shop.shop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByCategory(Category category);
}
