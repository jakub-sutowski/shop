package com.example.shop.shop.controller;

import com.example.shop.shop.model.dto.OpinionDto;
import com.example.shop.shop.service.OpinionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/opinion")
@RequiredArgsConstructor
@Log4j2
public class OpinionController {

    private final OpinionService opinionService;

    @Operation(
            summary = "Create opinion",
            description = "Creates a new opinion for a specific product."
    )
    @PostMapping("/{productCode}")
    public ResponseEntity<OpinionDto> createOpinion(
            @Valid @PathVariable("productCode") Long productCode,
            Principal principal,
            @RequestBody OpinionDto opinionDto) {
        OpinionDto createdOpinion = opinionService.createOpinion(principal, productCode, opinionDto);
        log.info("Opinion to product {} successfully created", productCode);
        return ResponseEntity.ok(createdOpinion);
    }
}
