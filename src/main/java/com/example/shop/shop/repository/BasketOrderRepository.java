package com.example.shop.shop.repository;

import com.example.shop.shop.model.entity.BasketOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BasketOrderRepository extends JpaRepository<BasketOrder, UUID> {
}
