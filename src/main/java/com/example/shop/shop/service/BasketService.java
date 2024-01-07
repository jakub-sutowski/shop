package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.ProductNotExist;
import com.example.shop.shop.exception.exceptions.UserNotExist;
import com.example.shop.shop.mapping.BasketMapper;
import com.example.shop.shop.model.entity.Basket;
import com.example.shop.shop.model.entity.BasketOrder;
import com.example.shop.shop.model.entity.Product;
import com.example.shop.shop.model.entity.User;
import com.example.shop.shop.model.request.BasketRequest;
import com.example.shop.shop.repository.BasketOrderRepository;
import com.example.shop.shop.repository.BasketRepository;
import com.example.shop.shop.repository.ProductRepository;
import com.example.shop.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public double getTotalAmount(BasketRequest request) {
        return request.getProducts().stream()
                .mapToDouble(product -> product.getQuantity() * product.getProduct().getPrice())
                .sum();
    }

    @Transactional
    public BasketRequest addProductToBasket(Principal principal, Long productId) {
        String mail = principal.getName();
        User user = userRepository.findByEmail(mail).orElseThrow(() -> new UserNotExist(mail));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotExist(productId.toString()));
        List<Basket> baskets = user.getBaskets();
        Basket newBasket = findUnpaidBasket(baskets);
        if (newBasket == null) {
            newBasket = createNewBasket(user, product);
            baskets.add(newBasket);
        } else {
            updateBasketWithProduct(newBasket, product, productId);
        }
        Basket save = basketRepository.save(newBasket);
        return basketMapper.convert(save);
    }

    private Basket findUnpaidBasket(List<Basket> baskets) {
        for (Basket basket : baskets) {
            if (!basket.isPaid()) {
                return basket;
            }
        }
        return null;
    }

    private Basket createNewBasket(User user, Product product) {
        Basket newBasket = new Basket();
        BasketOrder newBasketOrder = new BasketOrder();
        List<BasketOrder> basketOrders = new ArrayList<>();
        newBasket.setUser(user);
        newBasketOrder.setBasket(newBasket);
        newBasketOrder.setProduct(product);
        newBasketOrder.setQuantity(1);
        basketOrders.add(newBasketOrder);
        newBasket.setProducts(basketOrders);
        return newBasket;
    }

    private void updateBasketWithProduct(Basket basket, Product product, Long productId) {
        List<BasketOrder> productsInBasket = basket.getProducts();
        Optional<BasketOrder> existingOrder = productsInBasket.stream()
                .filter(order -> order.getProduct().getId().equals(productId))
                .findFirst();
        if (existingOrder.isPresent()) {
            existingOrder.get().setQuantity(existingOrder.get().getQuantity() + 1);
        } else {
            BasketOrder newBasketOrder = new BasketOrder();
            newBasketOrder.setProduct(product);
            newBasketOrder.setQuantity(1);
            newBasketOrder.setBasket(basket);
            productsInBasket.add(newBasketOrder);
        }
    }

    @Transactional
    public void changeBasketToPaid(Principal principal) {
        String mail = principal.getName();
        User user = userRepository.findByEmail(mail).orElseThrow(() -> new UserNotExist(mail));
        List<Basket> baskets = user.getBaskets();
        for (Basket basket : baskets) {
            if (!basket.isPaid()) {
                basket.setPaid(true);
                basketRepository.save(basket);
            }
        }
    }
}

