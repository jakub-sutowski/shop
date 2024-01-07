package com.example.shop.shop.mapping;

import com.example.shop.shop.model.entity.BasketOrder;
import com.example.shop.shop.model.request.BasketOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasketOrderMapper implements Mapper<BasketOrder, BasketOrderRequest> {
    private final ProductMapper productMapper;

    @Override
    public BasketOrderRequest convert(BasketOrder from) {
        return BasketOrderRequest.builder()
                .product(productMapper.convert(from.getProduct()))
                .quantity(from.getQuantity())
                .build();
    }

    @Override
    public BasketOrder reverse(BasketOrderRequest from) {
        return null;
    }
}
