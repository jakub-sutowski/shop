package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.UserNotExist;
import com.example.shop.shop.model.User;
import com.example.shop.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class HelloService {
    private final UserRepository userRepository;

    public String sayHello(Principal principal) {
        String mail = principal.getName();
        User user = userRepository.findByEmail(mail).orElseThrow(() -> new UserNotExist(principal.getName()));
        return "Hello, " + user.getFirstName();
    }
}
