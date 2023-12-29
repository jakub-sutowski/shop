package com.example.shop.shop.repository;

import com.example.shop.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean findUserByEmail(String email);
}
