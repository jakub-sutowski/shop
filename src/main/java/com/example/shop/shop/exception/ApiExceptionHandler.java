package com.example.shop.shop.exception;

import com.example.shop.shop.exception.exceptions.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
@Log4j2
public class ApiExceptionHandler {

    @ExceptionHandler(value = {UserAlreadyExist.class})
    public ResponseEntity<Object> handlerUserAlreadyExist(UserAlreadyExist exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now()
        );
        log.error("UserAlreadyExist: {}", exception.getMessage());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {UserNotExist.class})
    public ResponseEntity<Object> handlerUserNotExist(UserNotExist exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now()
        );
        log.error("UserNotExist: {}", exception.getMessage());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {ProductNotExist.class})
    public ResponseEntity<Object> handlerProductNotExist(ProductNotExist exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now()
        );
        log.error("ProductNotExist: {}", exception.getMessage());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {CategoryNotExist.class})
    public ResponseEntity<Object> handlerCategoryNotExist(CategoryNotExist exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now()
        );
        log.error("CategoryNotExist: {}", exception.getMessage());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {CategoryAlreadyExist.class})
    public ResponseEntity<Object> handlerCategoryAlreadyExist(CategoryAlreadyExist exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now()
        );
        log.error("CategoryAlreadyExist: {}", exception.getMessage());
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {BasketNotExist.class})
    public ResponseEntity<Object> handlerBasketNotExist(BasketNotExist exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now()
        );
        log.error("BasketNotExist: {}", exception.getMessage());
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
