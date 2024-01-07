package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.TokenRequestException;
import com.example.shop.shop.model.request.TokenRequest;
import com.example.shop.shop.model.request.UserRequest;
import com.example.shop.shop.model.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RestTemplate restTemplate;
    private final UserService userService;

    @Value("${token.app.base_url}")
    private String tokenBaseUrl;

    @Value("${token.app.generate_token_url}")
    private String tokenGenerateUrl;


    public String generate(Principal principal) {
        String generateTokenUrl = tokenBaseUrl + tokenGenerateUrl;
        UserRequest user = userService.getUser(principal);
        TokenRequest tokenRequest = new TokenRequest(user.getEmail());
        ResponseEntity<TokenResponse> tokenResponse = restTemplate.postForEntity(generateTokenUrl, tokenRequest, TokenResponse.class);
        return Optional.ofNullable(tokenResponse.getBody())
                .map(TokenResponse::getToken)
                .orElseThrow(TokenRequestException::new);
    }
}
