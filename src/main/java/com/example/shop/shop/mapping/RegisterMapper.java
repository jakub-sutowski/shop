package com.example.shop.shop.mapping;

import com.example.shop.shop.dto.request.RegisterRequest;
import com.example.shop.shop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterMapper implements Mapper<RegisterRequest, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User convert(RegisterRequest from) {
        return User.builder()
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .email(from.getEmail())
                .password(passwordEncoder.encode(from.getPassword()))
                .role(from.getRole())
                .build();
    }

    @Override
    public RegisterRequest reverse(User from) {
        return RegisterRequest.builder()
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .email(from.getEmail())
                .password(from.getPassword())
                .role(from.getRole())
                .build();
    }
}
