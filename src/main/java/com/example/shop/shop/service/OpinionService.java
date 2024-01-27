package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.ProductNotExist;
import com.example.shop.shop.exception.exceptions.UserNotExist;
import com.example.shop.shop.mapping.OpinionMapper;
import com.example.shop.shop.model.entity.Opinion;
import com.example.shop.shop.model.entity.Product;
import com.example.shop.shop.model.entity.User;
import com.example.shop.shop.model.dto.OpinionDto;
import com.example.shop.shop.repository.OpinionRepository;
import com.example.shop.shop.repository.ProductRepository;
import com.example.shop.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class OpinionService {

    private final ProductRepository productRepository;
    private final OpinionMapper opinionMapper;
    private final OpinionRepository opinionRepository;
    private final UserRepository userRepository;

    @Transactional
    public OpinionDto createOpinion(Principal principal, Long productCode, OpinionDto request) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotExist(email));

        Opinion opinionToSave = opinionMapper.reverseSave(request, user);
        Product product = productRepository.findProductByProductCode(productCode).orElseThrow(() -> new ProductNotExist(productCode.toString()));
        opinionToSave.setProduct(product);

        Opinion savedOpinion = opinionRepository.save(opinionToSave);

        return opinionMapper.convert(savedOpinion);
    }
}
