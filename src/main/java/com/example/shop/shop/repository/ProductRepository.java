package com.example.shop.shop.repository;

import com.example.shop.shop.model.entity.Category;
import com.example.shop.shop.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findProductByCategory(Category category, PageRequest pageRequest);
    Optional<Product> findProductByProductCode(Long productCode);
}
