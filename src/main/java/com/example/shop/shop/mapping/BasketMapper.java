package com.example.shop.shop.mapping;

import com.example.shop.shop.model.entity.Basket;
import com.example.shop.shop.model.entity.BasketOrder;
import com.example.shop.shop.model.dto.BasketOrderDto;
import com.example.shop.shop.model.dto.BasketDto;
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
                .paid(from.isPaid())
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
        List<BasketOrderDto> basketOrderDtos = new ArrayList<>();
        for (BasketOrder basketOrder : basketOrders) {
            basketOrderDtos.add(convertBasketOrder(basketOrder));
        }
        return basketOrderDtos;
    }
}
