package com.example.shop.shop.mapping;

import com.example.shop.shop.dto.BasketDto;
import com.example.shop.shop.dto.BasketOrderDto;
import com.example.shop.shop.model.Basket;
import com.example.shop.shop.model.BasketOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BasketMapper implements Mapper<Basket, BasketDto> {
    private final ProductMapper productMapper;

    @Override
    public BasketDto convert(Basket from) {
        return BasketDto.builder()
                .products(convertBasketOrderList(from.getProducts()))
                .isPaid(from.isPaid())
                .build();
    }

    @Override
    public Basket reverse(BasketDto from) {
        return null;
    }

    private BasketOrderDto convertBasketOrder(BasketOrder basketOrder) {
        return BasketOrderDto.builder()
                .product(productMapper.convert(basketOrder.getProduct()))
                .quantity(basketOrder.getQuantity())
                .build();
    }

    private List<BasketOrderDto> convertBasketOrderList(List<BasketOrder> basketOrders) {
        List<BasketOrderDto> basketOrdersDto = new ArrayList<>();
        for (BasketOrder basketOrder : basketOrders) {
            basketOrdersDto.add(convertBasketOrder(basketOrder));
        }
        return basketOrdersDto;
    }
}
