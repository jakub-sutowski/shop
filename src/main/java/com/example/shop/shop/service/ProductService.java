package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.ProductNotExist;
import com.example.shop.shop.mapping.OpinionMapper;
import com.example.shop.shop.mapping.ProductMapper;
import com.example.shop.shop.model.entity.Opinion;
import com.example.shop.shop.model.entity.Product;
import com.example.shop.shop.model.dto.OpinionDto;
import com.example.shop.shop.model.dto.ProductDto;
import com.example.shop.shop.repository.OpinionRepository;
import com.example.shop.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OpinionRepository opinionRepository;
    private final ProductMapper productMapper;
    private final OpinionMapper opinionMapper;

    public ProductDto getProduct(Long productCode) {
        Product product = getProductByProductCode(productCode);
        return productMapper.convert(product);
    }

    @Transactional
    public ProductDto createProduct(ProductDto request) {
        Product productToSave = productMapper.reverse(request);
        productToSave.setOpinions(new ArrayList<>());
        Product savedProduct = productRepository.save(productToSave);
        return productMapper.convert(savedProduct);
    }

    public Page<OpinionDto> getOpinionsByProduct(Long productCode, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Product product = getProductByProductCode(productCode);
        Page<Opinion> productOpinions = opinionRepository.findOpinionsByProduct(product, pageRequest);
        return productOpinions.map(opinionMapper::convert);
    }

    private Product getProductByProductCode(Long productCode) {
        return productRepository.findProductByProductCode(productCode).orElseThrow(() -> new ProductNotExist(productCode.toString()));
    }
}