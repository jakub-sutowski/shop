package com.example.shop.shop.controller;

import com.example.shop.shop.dto.request.BasketRequest;
import com.example.shop.shop.dto.request.ChangePasswordRequest;
import com.example.shop.shop.dto.request.UserRequest;
import com.example.shop.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<UserRequest> getUser(Principal principal) {
        UserRequest userRequest = userService.getUser(principal);
        return ResponseEntity.ok(userRequest);
    }

    @GetMapping("/history/")
    public ResponseEntity<List<BasketRequest>> getBasketHistory(Principal principal) {
        List<BasketRequest> basketHistory = userService.getBasketHistoryByUserId(principal);
        return ResponseEntity.ok(basketHistory);
    }

    @GetMapping("/basket/")
    public ResponseEntity<BasketRequest> getBasket(Principal principal) {
        BasketRequest basket = userService.getBasketByUser(principal);
        return ResponseEntity.ok(basket);
    }

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
