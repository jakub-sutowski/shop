package com.example.shop.shop.controller;

import com.example.shop.shop.dto.BasketDto;
import com.example.shop.shop.dto.UserDto;
import com.example.shop.shop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Long id) {
        UserDto userDto = userService.getUser(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<BasketDto>> getBasketHistory(@PathVariable("userId") Long id) {
        List<BasketDto> basketHistory = userService.getBasketHistoryByUserId(id);
        return ResponseEntity.ok(basketHistory);
    }

    @GetMapping("/basket/{userId}")
    public ResponseEntity<BasketDto> getBasket(@PathVariable("userId") Long id) {
        BasketDto basket = userService.getBasketByUserId(id);
        return ResponseEntity.ok(basket);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto userDto1 = userService.createUser(userDto);
        return ResponseEntity.ok(userDto1);
    }
}
