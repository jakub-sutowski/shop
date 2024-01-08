package com.example.shop.shop.util;

import lombok.NoArgsConstructor;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class StatusCodeTranslationUtil {

    private static final Map<String, String> codesTranslation = Map.of(
            "1", "Sukces",
            "2", "Niewłaściwy token",
            "3", "Brak środków",
            "4", "Brak produktów w koszyku",
            "5", "Użytkownik utworzony w token.app",
            "6", "Użytkownik już zarejstrowany w token.app",
            "7", "Użytkownik utworzony w bank.app",
            "8", "Użytkownik już zarejstrowany w bank.app"
    );

    public static String translateStatusCode(String statusCode) {
        return codesTranslation.get(statusCode);
    }
}
