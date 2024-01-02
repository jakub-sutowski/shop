package com.example.shop.shop.service;

import com.example.shop.shop.dto.request.OpinionRequest;
import com.example.shop.shop.dto.request.ProductRequest;
import com.example.shop.shop.exception.exceptions.ProductNotExist;
import com.example.shop.shop.mapping.OpinionMapper;
import com.example.shop.shop.mapping.ProductMapper;
import com.example.shop.shop.model.Opinion;
import com.example.shop.shop.model.Product;
import com.example.shop.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final OpinionMapper opinionMapper;

    public ProductRequest getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotExist(id.toString()));
        return productMapper.convert(product);
    }

    public ProductRequest createProduct(ProductRequest productRequest) {
        Product reverse = productMapper.reverse(productRequest);
        reverse.setOpinions(new ArrayList<>());
        Product save = productRepository.save(reverse);
        return productMapper.convert(save);
    }

    public List<OpinionRequest> getOpinionsByProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotExist(id.toString()));
        List<Opinion> opinions = product.getOpinions();
        return opinionMapper.convertList(opinions);
    }
}
