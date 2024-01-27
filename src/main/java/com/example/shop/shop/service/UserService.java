package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.UserNotExist;
import com.example.shop.shop.mapping.BasketMapper;
import com.example.shop.shop.mapping.UserMapper;
import com.example.shop.shop.model.entity.Basket;
import com.example.shop.shop.model.entity.User;
import com.example.shop.shop.model.dto.BasketDto;
import com.example.shop.shop.model.request.ChangePasswordRequest;
import com.example.shop.shop.model.dto.UserDto;
import com.example.shop.shop.repository.BasketRepository;
import com.example.shop.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto getUser(Principal principal) {
        User user = getLoggedUser(principal);
        return getUserDto(user);
    }

    public UserDto getUserDto(User user) {
        return userMapper.convert(user);
    }

    public User getLoggedUser(Principal principal) {
        String mail = principal.getName();
        return userRepository.findByEmail(mail).orElseThrow(() -> new UserNotExist(mail));
    }

    public Page<BasketDto> getBasketHistoryByUserId(Principal principal, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        User user = getLoggedUser(principal);
        Page<Basket> historyBaskets = basketRepository.findBasketsByUserAndPaidIs(user, pageRequest, true);
        return historyBaskets.map(basketMapper::convert);
    }

    public BasketDto getBasketByUser(Principal principal) {
        User user = getLoggedUser(principal);
        List<Basket> baskets = user.getBaskets();
        Basket newBasket = new Basket();

        for (Basket basket : baskets) {
            if (!basket.isPaid()) {
                newBasket = basket;
            }
        }
        return basketMapper.convert(newBasket);
    }

    @Transactional
    public void changePassword(ChangePasswordRequest request, Principal principal) {
        var user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}