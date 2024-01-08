package com.example.shop.shop.service;

import com.example.shop.shop.model.request.BasketRequest;
import com.example.shop.shop.model.request.PaymentRequest;
import com.example.shop.shop.util.StatusCodeTranslationUtil;
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

    private static final String SUCCESS_CODE = "1";
    private static final String EMPTY_BASKET_CODE = "4";

    public String pay(Principal principal) {
        try {
            BasketRequest basketByUser = userService.getBasketByUser(principal);
            double totalAmount = basketService.getTotalAmount(basketByUser);
            String token = tokenService.generate(principal);
            PaymentRequest paymentRequest = new PaymentRequest(principal.getName(), token, totalAmount);
            String statusCode = bankService.pay(paymentRequest);
            String status = StatusCodeTranslationUtil.translateStatusCode(statusCode);
            if (statusCode.equals(SUCCESS_CODE)) {
                basketService.changeBasketToPaid(principal);
            }
            return status;
        } catch (Exception e) {
            return StatusCodeTranslationUtil.translateStatusCode(EMPTY_BASKET_CODE);
        }
    }
}
