package com.example.demo.shop.service;

import com.example.demo.shop.dto.OpinionDto;
import com.example.demo.shop.mapping.OpinionMapper;
import com.example.demo.shop.model.Opinion;
import com.example.demo.shop.model.Product;
import com.example.demo.shop.model.User;
import com.example.demo.shop.repository.OpinionRepository;
import com.example.demo.shop.repository.ProductRepository;
import com.example.demo.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpinionService {
    private final ProductRepository productRepository;
    private final OpinionMapper opinionMapper;
    private final OpinionRepository opinionRepository;
    private final UserRepository userRepository;

    public OpinionDto createOpinion(Long userId, Long productId, OpinionDto opinionDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Opinion save = opinionMapper.reverseSave(opinionDto, user);
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        save.setProduct(product);
        Opinion save1 = opinionRepository.save(save);
        return opinionMapper.convert(save1);
    }


}
