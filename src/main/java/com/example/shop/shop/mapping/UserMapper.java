package com.example.shop.shop.mapping;

import com.example.shop.shop.model.entity.User;
import com.example.shop.shop.model.request.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserRequest> {

    @Override
    public UserRequest convert(User from) {
        return UserRequest.builder()
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .email(from.getEmail())
                .build();
    }

    @Override
    public User reverse(UserRequest from) {
        return User.builder()
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .email(from.getEmail())
                .build();
    }
}
