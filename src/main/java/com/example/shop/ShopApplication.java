package com.example.shop;

import com.example.shop.shop.dto.request.RegisterRequest;
import com.example.shop.shop.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.example.shop.shop.type.Role.ADMIN;
import static com.example.shop.shop.type.Role.USER;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstName("Zwyklak")
                    .lastName("Admin")
                    .email("zwyklak@mail.com")
                    .password("password")
                    .role(USER)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

        };
    }
}

