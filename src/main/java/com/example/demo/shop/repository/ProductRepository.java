package com.example.demo.shop.repository;

import com.example.demo.shop.model.Category;
import com.example.demo.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByName(String name);

    List<Product> findProductByCategory(Category category);
}
