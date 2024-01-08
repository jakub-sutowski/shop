package com.example.shop;

import com.example.shop.shop.model.request.CategoryRequest;
import com.example.shop.shop.model.request.ProductRequest;
import com.example.shop.shop.model.request.RegisterRequest;
import com.example.shop.shop.service.AuthenticationService;
import com.example.shop.shop.service.CategoryService;
import com.example.shop.shop.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.example.shop.shop.type.Role.ADMIN;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService authenticationService,
            ProductService productService,
            CategoryService categoryService
    ) {
        return args -> {
            var category1 = CategoryRequest.builder()
                    .name("Elektronika")
                    .build();
            categoryService.createCategory(category1);

            var product1 = ProductRequest.builder()
                    .productCode(1L)
                    .name("Telefon")
                    .price(1599)
                    .category("Elektronika")
                    .build();
            productService.createProduct(product1);

            var product2 = ProductRequest.builder()
                    .productCode(2L)
                    .name("Monitor")
                    .price(599)
                    .category("Elektronika")
                    .build();
            productService.createProduct(product2);

            var admin = RegisterRequest.builder()
                    .firstName("Zwyklak")
                    .lastName("Admin")
                    .email("fdfhd@mail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + authenticationService.register(admin).getAccessToken());

        };
    }
}

