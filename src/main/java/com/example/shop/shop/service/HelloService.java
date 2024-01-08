package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.UserNotExist;
import com.example.shop.shop.model.entity.User;
import com.example.shop.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class HelloService {

    private final UserRepository userRepository;

    public String sayHello(Principal principal) {
        String email = principal.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotExist(email));

        return "Hello, " + user.getFirstName() + "!";
    }
}
