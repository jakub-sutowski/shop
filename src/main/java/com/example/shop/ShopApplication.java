package com.example.shop;

import com.example.shop.shop.model.request.RegisterRequest;
import com.example.shop.shop.service.AuthenticationService;
import com.example.shop.shop.service.CategoryService;
import com.example.shop.shop.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.shop.shop.type.Role.ADMIN;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService authenticationService
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstName("Boss")
                    .lastName("Admin")
                    .email("admin@mail.com")
                    .password("password123")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + authenticationService.register(admin).getAccessToken());

            var user = RegisterRequest.builder()
                    .firstName("Zwyklak")
                    .lastName("User")
                    .email("user@mail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("User token: " + authenticationService.register(user).getAccessToken());

        };
    }
}

