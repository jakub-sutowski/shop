package com.example.shop.shop.repository;

import com.example.shop.shop.model.entity.Basket;
import com.example.shop.shop.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

public interface BasketRepository extends JpaRepository<Basket, UUID> {
    Page<Basket> findBasketsByUserAndPaidIs(User user, PageRequest pageRequest, boolean paid);
}
