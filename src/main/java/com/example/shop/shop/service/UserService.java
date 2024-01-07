package com.example.shop.shop.service;

import com.example.shop.shop.exception.exceptions.UserNotExist;
import com.example.shop.shop.mapping.BasketMapper;
import com.example.shop.shop.mapping.RegisterMapper;
import com.example.shop.shop.mapping.UserMapper;
import com.example.shop.shop.model.entity.Basket;
import com.example.shop.shop.model.entity.User;
import com.example.shop.shop.model.request.BasketRequest;
import com.example.shop.shop.model.request.ChangePasswordRequest;
import com.example.shop.shop.model.request.UserRequest;
import com.example.shop.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BasketMapper basketMapper;
    private final UserMapper userMapper;
    private final RegisterMapper registerMapper;
    private final PasswordEncoder passwordEncoder;

    public UserRequest getUser(Principal principal) {
        String mail = principal.getName();
        User user = userRepository.findByEmail(mail).orElseThrow(() -> new UserNotExist(mail));
        return getUserDto(user);
    }

    public UserRequest getUserDto(User user) {
        return userMapper.convert(user);
    }


    public List<BasketRequest> getBasketHistoryByUserId(Principal principal) {
        String mail = principal.getName();
        User user = userRepository.findByEmail(mail).orElseThrow(() -> new UserNotExist(mail));
        List<Basket> baskets = user.getBaskets();
        List<BasketRequest> basketHistory = new ArrayList<>();
        for (Basket basket : baskets) {
            if (basket.isPaid()) {
                basketHistory.add(basketMapper.convert(basket));
            }
        }
        return basketHistory;
    }

    public BasketRequest getBasketByUser(Principal principal) {
        String mail = principal.getName();
        User user = userRepository.findByEmail(mail).orElseThrow(() -> new UserNotExist(mail));
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
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }
}
