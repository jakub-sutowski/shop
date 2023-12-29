package com.example.shop.shop.validation;

import com.example.shop.shop.dto.UserDto;
import com.example.shop.shop.exception.exceptions.UserAlreadyExist;
import com.example.shop.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void register(UserDto userDto) {
        if (userRepository.findUserByEmail(userDto.getEmail())) {
            throw new UserAlreadyExist(userDto.getEmail());
        }
    }
}
