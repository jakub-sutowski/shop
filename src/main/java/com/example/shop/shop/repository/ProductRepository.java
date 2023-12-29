package com.example.shop.shop.repository;

import com.example.shop.shop.model.Category;
import com.example.shop.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByName(String name);

    List<Product> findProductByCategory(Category category);
}
