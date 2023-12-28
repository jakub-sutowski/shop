package com.example.demo.shop.mapping;

import com.example.demo.shop.dto.BasketOrderDto;
import com.example.demo.shop.model.BasketOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasketOrderMapper implements Mapper<BasketOrder, BasketOrderDto> {
    private final ProductMapper productMapper;

    @Override
    public BasketOrderDto convert(BasketOrder from) {
        return BasketOrderDto.builder()
                .product(productMapper.convert(from.getProduct()))
                .quantity(from.getQuantity())
                .build();
    }

    @Override
    public BasketOrder reverse(BasketOrderDto from) {
        return null;
    }
}
