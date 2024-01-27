package com.example.shop.shop.controller;

import com.example.shop.shop.model.request.AuthenticationRequest;
import com.example.shop.shop.model.request.RegisterRequest;
import com.example.shop.shop.model.response.AuthenticationResponse;
import com.example.shop.shop.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Register a new user",
            description = "Registers a new user with the provided details.")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        log.info("User {} successfully created", request.getEmail());
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Operation(
            summary = "Authenticate user",
            description = "Authenticates a user based on the provided credentials."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User successfully authenticated"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid credentials"
            )
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        log.info("User {} successfully logged", request.getEmail());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @Operation(
            summary = "Refresh authentication token",
            description = "Refreshes the authentication token."
    )
    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        authenticationService.refreshToken(httpServletRequest, httpServletResponse);
    }
}
