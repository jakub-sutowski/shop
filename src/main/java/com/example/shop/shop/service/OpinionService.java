package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.ProductNotExist;
import com.example.shop.shop.exception.exceptions.UserNotExist;
import com.example.shop.shop.mapping.OpinionMapper;
import com.example.shop.shop.model.entity.Opinion;
import com.example.shop.shop.model.entity.Product;
import com.example.shop.shop.model.entity.User;
import com.example.shop.shop.model.request.OpinionRequest;
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
    public OpinionRequest createOpinion(Principal principal, Long productId, OpinionRequest request) {
        String mail = principal.getName();
        User user = userRepository.findByEmail(mail).orElseThrow(() -> new UserNotExist(mail));
        Opinion save = opinionMapper.reverseSave(request, user);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotExist(productId.toString()));
        save.setProduct(product);
        Opinion save1 = opinionRepository.save(save);
        return opinionMapper.convert(save1);
    }


}
