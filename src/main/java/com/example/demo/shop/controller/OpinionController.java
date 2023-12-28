package com.example.demo.shop.controller;

import com.example.demo.shop.dto.OpinionDto;
import com.example.demo.shop.service.OpinionService;
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
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId,
            @RequestBody OpinionDto opinionDto) {
        OpinionDto opinionDto1 = opinionService.createOpinion(userId, productId, opinionDto);
        return ResponseEntity.ok(opinionDto1);
    }
}
