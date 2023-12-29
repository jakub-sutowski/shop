package com.example.shop.shop.repository;

import com.example.shop.shop.model.BasketOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketOrderRepository extends JpaRepository<BasketOrder, Long> {
}