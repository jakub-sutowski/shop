package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.BankRequestException;
import com.example.shop.shop.model.request.PaymentRequest;
import com.example.shop.shop.model.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService {

    private final RestTemplate restTemplate;

    @Value("${bank.app.base_url}")
    private String bankBaseUrl;

    @Value("${bank.app.pay_url}")
    private String bankPayUrl;

    public String pay(PaymentRequest request) {
        String payUrl = bankBaseUrl + bankPayUrl;
        ResponseEntity<PaymentResponse> paymentResponse = restTemplate.postForEntity(payUrl, request, PaymentResponse.class);
        return Optional.ofNullable(paymentResponse.getBody())
                .map(PaymentResponse::getStatusCode)
                .orElseThrow(BankRequestException::new);
    }
}
