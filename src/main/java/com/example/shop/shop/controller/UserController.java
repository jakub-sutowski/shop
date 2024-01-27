package com.example.shop.shop.controller;

import com.example.shop.shop.model.dto.BasketDto;
import com.example.shop.shop.model.request.ChangePasswordRequest;
import com.example.shop.shop.model.dto.UserDto;
import com.example.shop.shop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Get user details",
            description = "Retrieves details of the authenticated user."
    )
    @GetMapping
    public ResponseEntity<UserDto> getUser(Principal principal) {
        UserDto userDto = userService.getUser(principal);
        return ResponseEntity.ok(userDto);
    }

    @Operation(
            summary = "Get basket history",
            description = "Retrieves the basket history for the authenticated user."
    )
    @GetMapping("/history")
    public ResponseEntity<Page<BasketDto>> getBasketHistory(Principal principal,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Page<BasketDto> basketHistory = userService.getBasketHistoryByUserId(principal, page, size);
        return ResponseEntity.ok(basketHistory);
    }

    @Operation(
            summary = "Get current basket",
            description = "Retrieves the current basket for the authenticated user."
    )
    @GetMapping("/basket")
    public ResponseEntity<BasketDto> getBasket(Principal principal) {
        BasketDto basket = userService.getBasketByUser(principal);
        return ResponseEntity.ok(basket);
    }

    @Operation(
            summary = "Change password",
            description = "Changes the password for the authenticated user."
    )
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
