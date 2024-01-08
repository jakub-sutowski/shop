package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.BankRequestException;
import com.example.shop.shop.exception.exceptions.TokenRequestException;
import com.example.shop.shop.model.entity.Token;
import com.example.shop.shop.model.entity.User;
import com.example.shop.shop.model.request.UserBankRequest;
import com.example.shop.shop.model.request.UserTokenRequest;
import com.example.shop.shop.model.request.AuthenticationRequest;
import com.example.shop.shop.model.request.RegisterRequest;
import com.example.shop.shop.model.response.AuthenticationResponse;
import com.example.shop.shop.model.response.PaymentResponse;
import com.example.shop.shop.model.response.UserBankResponse;
import com.example.shop.shop.model.response.UserTokenResponse;
import com.example.shop.shop.repository.TokenRepository;
import com.example.shop.shop.repository.UserRepository;
import com.example.shop.shop.type.TokenType;
import com.example.shop.shop.util.StatusCodeTranslationUtil;
import com.example.shop.shop.validation.UserValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserValidator userValidator;
    private final RestTemplate restTemplate;

    @Value("${token.app.base_url}")
    private String tokenBaseUrl;

    @Value("${token.app.register_url}")
    private String tokenAddUserUrl;

    @Value("${bank.app.base_url}")
    private String bankBaseUrl;

    @Value("${bank.app.register_url}")
    private String bankAddUserUrl;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        userValidator.register(request.getEmail());
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        User save = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(save, jwtToken);
        String tokenResponse = registerToToken(user);
        String bankResponse = registerToBank(user);
        log.info(StatusCodeTranslationUtil.translateStatusCode(tokenResponse));
        log.info(StatusCodeTranslationUtil.translateStatusCode(bankResponse));
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private String registerToBank(User user) {
        String registerBankUrl = bankBaseUrl + bankAddUserUrl;
        UserBankRequest userBankRequest = UserBankRequest.builder().email(user.getEmail()).build();
        ResponseEntity<UserBankResponse> response = restTemplate.postForEntity(registerBankUrl, userBankRequest, UserBankResponse.class);
        return Optional.ofNullable(response.getBody())
                .map(UserBankResponse::getStatusCode)
                .orElseThrow(BankRequestException::new);
    }

    private String registerToToken(User user) {
        String registerTokenUrl = tokenBaseUrl + tokenAddUserUrl;
        UserTokenRequest userTokenRequest = UserTokenRequest.builder().email(user.getEmail()).build();
        ResponseEntity<UserTokenResponse> response = restTemplate.postForEntity(registerTokenUrl, userTokenRequest, UserTokenResponse.class);
        return Optional.ofNullable(response.getBody())
                .map(UserTokenResponse::getStatusCode)
                .orElseThrow(TokenRequestException::new);
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> allValidTokenByUser = tokenRepository.findAllValidTokenByUser(user.getId());
        if (allValidTokenByUser.isEmpty())
            return;
        allValidTokenByUser.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(allValidTokenByUser);
    }

    @Transactional
    public void refreshToken(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        final String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(httpServletResponse.getOutputStream(), authenticationResponse);
            }
        }
    }
}
