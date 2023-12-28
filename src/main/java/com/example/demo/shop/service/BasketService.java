package com.example.demo.shop.service;

import com.example.demo.shop.dto.BasketDto;
import com.example.demo.shop.mapping.BasketMapper;
import com.example.demo.shop.model.Basket;
import com.example.demo.shop.model.BasketOrder;
import com.example.demo.shop.model.Product;
import com.example.demo.shop.model.User;
import com.example.demo.shop.repository.BasketOrderRepository;
import com.example.demo.shop.repository.BasketRepository;
import com.example.demo.shop.repository.ProductRepository;
import com.example.demo.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final BasketOrderRepository basketOrderRepository;
    private final BasketMapper basketMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public BasketDto getBasket(Long id) {
        Basket basket = basketRepository.findById(id).orElse(new Basket());
        return basketMapper.convert(basket);
    }

    public BasketDto addProductToBasket(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        Basket newBasket = new Basket();
        BasketOrder newBasketOrder = new BasketOrder();
        List<Basket> baskets = user.getBaskets();
        if (baskets.isEmpty()) {
            fillNewBasket(user, product, newBasket, newBasketOrder, baskets);
        } else {
            for (Basket basket : baskets) {
                if (!basket.isPaid()) {
                    List<BasketOrder> productsInBasket = basket.getProducts();
                    for (BasketOrder productInBasket : productsInBasket) {
                        if (productInBasket.getProduct().getId().equals(productId)) {
                            productInBasket.setQuantity(productInBasket.getQuantity() + 1);
                            newBasketOrder = productInBasket;
                            newBasket = basket;
                            break;
                        } else {
                            newBasketOrder.setProduct(product);
                            newBasketOrder.setQuantity(1);
                            newBasketOrder.setBasket(basket);
                            newBasket = basket;
                        }
                    }
                } else {
                    fillNewBasket(user, product, newBasket, newBasketOrder, baskets);
                }
            }
        }
        Basket save = basketRepository.save(newBasket);
        basketOrderRepository.save(newBasketOrder);
        return basketMapper.convert(save);
    }

    private void fillNewBasket(User user, Product product, Basket newBasket, BasketOrder newBasketOrder, List<Basket> baskets) {
        newBasket.setUser(user);
        ArrayList<BasketOrder> basketOrders = new ArrayList<>();
        newBasketOrder.setBasket(newBasket);
        newBasketOrder.setProduct(product);
        newBasketOrder.setQuantity(1);
        basketOrders.add(newBasketOrder);
        newBasket.setProducts(basketOrders);
        baskets.add(newBasket);
    }
}

