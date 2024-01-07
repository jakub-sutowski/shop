package com.example.shop.shop.repository;

import com.example.shop.shop.model.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {

}
