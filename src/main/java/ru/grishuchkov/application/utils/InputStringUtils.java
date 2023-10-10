package ru.grishuchkov.application.utils;

import jakarta.servlet.http.HttpServletRequest;

public class InputStringUtils {

    public static String parseCurrencyPathInfo(HttpServletRequest request) {
        String currencyCode = getUpperCaseParsedString(request);

        return currencyCode;
    }

    public static String parseExchangePathInfo(HttpServletRequest request) {
        String parsedString = getUpperCaseParsedString(request);

        return parsedString;
    }

    private static String getUpperCaseParsedString(HttpServletRequest request) {
        return request.getPathInfo().replace("/", "").toUpperCase();
    }
}
