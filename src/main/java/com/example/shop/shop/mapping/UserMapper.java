package com.example.shop.shop.mapping;

import com.example.shop.shop.model.entity.User;
import com.example.shop.shop.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public UserDto convert(User from) {
        return UserDto.builder()
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .email(from.getEmail())
                .build();
    }

    @Override
    public User reverse(UserDto from) {
        return User.builder()
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .email(from.getEmail())
                .build();
    }
}
