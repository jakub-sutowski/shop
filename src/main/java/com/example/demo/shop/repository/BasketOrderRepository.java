package com.example.demo.shop.repository;

import com.example.demo.shop.model.BasketOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketOrderRepository extends JpaRepository<BasketOrder, Long> {
}
