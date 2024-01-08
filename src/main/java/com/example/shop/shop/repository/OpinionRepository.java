package com.example.shop.shop.repository;

import com.example.shop.shop.model.entity.Opinion;
import com.example.shop.shop.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OpinionRepository extends JpaRepository<Opinion, UUID> {
    Page<Opinion> findOpinionsByProduct(Product product, PageRequest pageRequest);
}
