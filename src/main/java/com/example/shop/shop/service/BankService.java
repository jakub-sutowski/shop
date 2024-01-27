package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.BankRequestException;
import com.example.shop.shop.model.request.PaymentRequest;
import com.example.shop.shop.model.response.StatusResponse;
import com.example.shop.shop.type.StatusCode;
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

    public StatusCode pay(PaymentRequest request) {
        String payUrl = bankBaseUrl + bankPayUrl;

        ResponseEntity<StatusResponse> paymentResponse = restTemplate.postForEntity(payUrl, request, StatusResponse.class);

        return Optional.ofNullable(paymentResponse.getBody())
                .map(StatusResponse::getStatusCode)
                .orElseThrow(BankRequestException::new);
    }
}
