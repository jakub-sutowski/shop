package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.ProductNotExist;
import com.example.shop.shop.mapping.OpinionMapper;
import com.example.shop.shop.mapping.ProductMapper;
import com.example.shop.shop.model.entity.Opinion;
import com.example.shop.shop.model.entity.Product;
import com.example.shop.shop.model.request.OpinionRequest;
import com.example.shop.shop.model.request.ProductRequest;
import com.example.shop.shop.repository.OpinionRepository;
import com.example.shop.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OpinionRepository opinionRepository;
    private final ProductMapper productMapper;
    private final OpinionMapper opinionMapper;

    public ProductRequest getProduct(Long productCode) {
        Product product = getProductByProductCode(productCode);
        return productMapper.convert(product);
    }

    @Transactional
    public ProductRequest createProduct(ProductRequest request) {
        Product productToSave = productMapper.reverse(request);
        productToSave.setOpinions(new ArrayList<>());
        Product savedProduct = productRepository.save(productToSave);
        return productMapper.convert(savedProduct);
    }

    public Page<OpinionRequest> getOpinionsByProduct(Long productCode, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Product product = getProductByProductCode(productCode);
        Page<Opinion> productOpinions = opinionRepository.findOpinionsByProduct(product, pageRequest);
        return productOpinions.map(opinionMapper::convert);
    }

    private Product getProductByProductCode(Long productCode) {
        return productRepository.findProductByProductCode(productCode).orElseThrow(() -> new ProductNotExist(productCode.toString()));
    }
}