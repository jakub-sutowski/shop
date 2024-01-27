package com.example.shop.shop.service;

import com.example.shop.shop.model.dto.BasketDto;
import com.example.shop.shop.model.request.PaymentRequest;
import com.example.shop.shop.repository.BasketRepository;
import com.example.shop.shop.type.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final BasketService basketService;
    private final UserService userService;
    private final TokenService tokenService;
    private final BankService bankService;
    private final BasketRepository basketRepository;

    public StatusCode pay(Principal principal) {
        try {
            BasketDto basketByUser = userService.getBasketByUser(principal);
            double totalAmount = basketService.getTotalAmount(basketByUser);
            String token = tokenService.generate(principal);
            PaymentRequest paymentRequest = new PaymentRequest(principal.getName(), token, totalAmount);
            StatusCode statusCode = bankService.pay(paymentRequest);
            if (statusCode.equals(StatusCode.SUCCESS)) {
                basketService.changeBasketToPaid(principal);
            }
            return StatusCode.SUCCESS;
        } catch (Exception e) {
            return StatusCode.EMPTY_BASKET;
        }
    }
}
