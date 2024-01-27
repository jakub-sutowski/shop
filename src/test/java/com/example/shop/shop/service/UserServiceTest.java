package com.example.shop.shop.service;

import com.example.shop.shop.mapping.BasketMapper;
import com.example.shop.shop.mapping.UserMapper;
import com.example.shop.shop.repository.BasketRepository;
import com.example.shop.shop.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BasketRepository basketRepository;
    @Mock
    private BasketMapper basketMapper;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGetUser() {

        Principal mockPrincipal = mock(Principal.class);
    }
    @Test
    void getUserDto() {
    }

}