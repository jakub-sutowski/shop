package com.example.demo.shop.service;

import com.example.demo.shop.dto.OpinionDto;
import com.example.demo.shop.dto.ProductDto;
import com.example.demo.shop.mapping.OpinionMapper;
import com.example.demo.shop.mapping.ProductMapper;
import com.example.demo.shop.model.Opinion;
import com.example.demo.shop.model.Product;
import com.example.demo.shop.repository.ProductRepository;
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

    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        return productMapper.convert(product);
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product reverse = productMapper.reverse(productDto);
        reverse.setOpinions(new ArrayList<>());
        Product save = productRepository.save(reverse);
        return productMapper.convert(save);
    }

    public List<OpinionDto> getOpinionsByProduct(Long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        List<Opinion> opinions = product.getOpinions();
        return opinionMapper.convertList(opinions);
    }
}
