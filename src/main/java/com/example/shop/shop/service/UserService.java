package com.example.shop.shop.service;

import com.example.shop.shop.dto.BasketDto;
import com.example.shop.shop.dto.UserDto;
import com.example.shop.shop.exception.exceptions.UserNotExist;
import com.example.shop.shop.mapping.BasketMapper;
import com.example.shop.shop.mapping.UserMapper;
import com.example.shop.shop.model.Basket;
import com.example.shop.shop.model.User;
import com.example.shop.shop.repository.UserRepository;
import com.example.shop.shop.validation.UserValidator;
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
    private final UserValidator userValidator;

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotExist(id.toString()));
        return getUserDto(user);
    }

    private UserDto getUserDto(User user) {
        return userMapper.convert(user);
    }

    public UserDto createUser(UserDto userDto) {
        userValidator.register(userDto);
        User save = userRepository.save(userMapper.reverse(userDto));
        return getUserDto(save);
    }

    public List<BasketDto> getBasketHistoryByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotExist(id.toString()));
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
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotExist(id.toString()));
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
