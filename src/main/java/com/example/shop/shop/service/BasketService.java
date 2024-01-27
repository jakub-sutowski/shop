package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.ProductNotExist;
import com.example.shop.shop.mapping.BasketMapper;
import com.example.shop.shop.model.entity.Basket;
import com.example.shop.shop.model.entity.BasketOrder;
import com.example.shop.shop.model.entity.Product;
import com.example.shop.shop.model.entity.User;
import com.example.shop.shop.model.dto.BasketDto;
import com.example.shop.shop.repository.BasketRepository;
import com.example.shop.shop.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final UserService userService;

    public double getTotalAmount(BasketDto request) {
        return request.getProducts().stream()
                .mapToDouble(product -> product.getQuantity() * product.getProduct().getPrice())
                .sum();
    }

    @Transactional
    public BasketDto addProductToBasket(Principal principal, Long productCode) {
        User user = userService.getLoggedUser(principal);
        Product product = productRepository.findProductByProductCode(productCode).orElseThrow(() -> new ProductNotExist(productCode.toString()));
        List<Basket> baskets = user.getBaskets();
        Optional<Basket> unpaidBasket = findUnpaidBasket(baskets);

        if (unpaidBasket.isPresent()) {
            updateBasketWithProduct(unpaidBasket.get(), product, productCode);
            Basket savedBasket = basketRepository.save(unpaidBasket.get());
            return basketMapper.convert(savedBasket);
        } else {
            Basket newBasket = createNewBasket(user, product);
            baskets.add(newBasket);
            Basket savedBasket = basketRepository.save(newBasket);
            return basketMapper.convert(savedBasket);
        }
    }

    private Optional<Basket> findUnpaidBasket(List<Basket> baskets) {
        return baskets.stream()
                .filter(basket -> !basket.isPaid())
                .findFirst();
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

    private void updateBasketWithProduct(Basket basket, Product product, Long productCode) {
        List<BasketOrder> productsInBasket = basket.getProducts();
        Optional<BasketOrder> existingOrder = productsInBasket.stream()
                .filter(order -> order.getProduct().getProductCode().equals(productCode))
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
        User user = userService.getLoggedUser(principal);
        List<Basket> baskets = user.getBaskets();

        for (Basket basket : baskets) {
            if (!basket.isPaid()) {
                basket.setPaid(true);
                basketRepository.save(basket);
            }
        }
    }
}
