package com.example.shop.shop.mapping;

import com.example.shop.shop.model.entity.BasketOrder;
import com.example.shop.shop.model.dto.BasketOrderDto;
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
