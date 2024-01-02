package com.example.shop.shop.controller;

import com.example.shop.shop.dto.request.OpinionRequest;
import com.example.shop.shop.service.OpinionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/opinion")
@RequiredArgsConstructor
public class OpinionController {

    private final OpinionService opinionService;

    @PostMapping("/{productId}/")
    public ResponseEntity<OpinionRequest> createOpinion(
            @Valid
            @PathVariable("productId") Long productId,
            Principal principal,
            @RequestBody OpinionRequest opinionRequest) {
        OpinionRequest opinionRequest1 = opinionService.createOpinion(principal, productId, opinionRequest);
        return ResponseEntity.ok(opinionRequest1);
    }
}
