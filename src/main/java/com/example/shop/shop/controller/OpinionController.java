package com.example.shop.shop.controller;

import com.example.shop.shop.model.request.OpinionRequest;
import com.example.shop.shop.service.OpinionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/opinion")
@RequiredArgsConstructor
@Log4j2
public class OpinionController {

    private final OpinionService opinionService;

    @PostMapping("/{productId}")
    public ResponseEntity<OpinionRequest> createOpinion(
            @Valid @PathVariable("productId") Long productId,
            Principal principal,
            @RequestBody OpinionRequest opinionRequest) {
        OpinionRequest createdOpinion = opinionService.createOpinion(principal, productId, opinionRequest);
        log.info("Opinion to product {} successfully created", productId);
        return ResponseEntity.ok(createdOpinion);
    }
}
