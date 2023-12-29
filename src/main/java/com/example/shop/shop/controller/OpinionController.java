package com.example.shop.shop.controller;

import com.example.shop.shop.dto.OpinionDto;
import com.example.shop.shop.service.OpinionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/opinion")
@RequiredArgsConstructor
public class OpinionController {
    private final OpinionService opinionService;

    @PostMapping("/{productId}/{userId}")
    public ResponseEntity<OpinionDto> createOpinion(
            @Valid
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId,
            @RequestBody OpinionDto opinionDto) {
        OpinionDto opinionDto1 = opinionService.createOpinion(userId, productId, opinionDto);
        return ResponseEntity.ok(opinionDto1);
    }
}
