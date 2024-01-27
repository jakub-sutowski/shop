package com.example.shop.shop.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusCode {
    SUCCESS,
    EMPTY_BASKET,
    INVALID_TOKEN,
    INSUFFICIENT_BALANCE,
    USER_CREATED,
    USER_ALREADY_EXIST
}
