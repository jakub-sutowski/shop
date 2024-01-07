package com.example.shop.shop.mapping;

import com.example.shop.shop.model.entity.Basket;
import com.example.shop.shop.model.entity.BasketOrder;
import com.example.shop.shop.model.request.BasketOrderRequest;
import com.example.shop.shop.model.request.BasketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BasketMapper implements Mapper<Basket, BasketRequest> {

    private final ProductMapper productMapper;

    @Override
    public BasketRequest convert(Basket from) {
        return BasketRequest.builder()
                .products(convertBasketOrderList(from.getProducts()))
                .isPaid(from.isPaid())
                .build();
    }

    @Override
    public Basket reverse(BasketRequest from) {
        return null;
    }

    private BasketOrderRequest convertBasketOrder(BasketOrder basketOrder) {
        return BasketOrderRequest.builder()
                .product(productMapper.convert(basketOrder.getProduct()))
                .quantity(basketOrder.getQuantity())
                .build();
    }

    private List<BasketOrderRequest> convertBasketOrderList(List<BasketOrder> basketOrders) {
        List<BasketOrderRequest> basketOrderRequests = new ArrayList<>();
        for (BasketOrder basketOrder : basketOrders) {
            basketOrderRequests.add(convertBasketOrder(basketOrder));
        }
        return basketOrderRequests;
    }
}
