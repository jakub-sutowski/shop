package com.example.demo.shop.service;

import com.example.demo.shop.dto.BasketDto;
import com.example.demo.shop.dto.UserDto;
import com.example.demo.shop.mapping.BasketMapper;
import com.example.demo.shop.mapping.UserMapper;
import com.example.demo.shop.model.Basket;
import com.example.demo.shop.model.User;
import com.example.demo.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BasketMapper basketMapper;
    private final UserMapper userMapper;

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElse(new User());
        return getUserDto(user);
    }

    private UserDto getUserDto(User user) {
        return userMapper.convert(user);
    }

    public UserDto createUser(UserDto userDto) {
        User save = userRepository.save(userMapper.reverse(userDto));
        return getUserDto(save);
    }

    public List<BasketDto> getBasketHistoryByUserId(Long id) {
        User user = userRepository.findById(id).orElse(new User());
        List<Basket> baskets = user.getBaskets();
        List<BasketDto> basketHistory = new ArrayList<>();
        for (Basket basket : baskets) {
            if (basket.isPaid()) {
                basketHistory.add(basketMapper.convert(basket));
            }
        }
        return basketHistory;
    }

    public BasketDto getBasketByUserId(Long id) {
        User user = userRepository.findById(id).orElse(new User());
        List<Basket> baskets = user.getBaskets();
        Basket newBasket = new Basket();
        for (Basket basket : baskets) {
            if (!basket.isPaid()) {
                newBasket = basket;
            }
        }
        return basketMapper.convert(newBasket);
    }
}
