package com.example.shop.shop.controller;

import com.example.shop.shop.model.request.BasketRequest;
import com.example.shop.shop.model.request.ChangePasswordRequest;
import com.example.shop.shop.model.request.UserRequest;
import com.example.shop.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<UserRequest> getUser(Principal principal) {
        UserRequest userRequest = userService.getUser(principal);
        return ResponseEntity.ok(userRequest);
    }

    @GetMapping("/history")
    public ResponseEntity<Page<BasketRequest>> getBasketHistory(Principal principal,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        Page<BasketRequest> basketHistory = userService.getBasketHistoryByUserId(principal, page, size);
        return ResponseEntity.ok(basketHistory);
    }

    @GetMapping("/basket")
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
        log.info("Password for {} successfully changed", connectedUser.getName());
        return ResponseEntity.ok().build();
    }
}
