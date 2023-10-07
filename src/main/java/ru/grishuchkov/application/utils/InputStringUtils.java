package ru.grishuchkov.application.utils;

import jakarta.servlet.http.HttpServletRequest;

public class InputStringUtils {

    public static String parsePathInfo(HttpServletRequest request){
        String currencyCode = request.getPathInfo().replace("/", "").toUpperCase();

        return currencyCode;
    }
}
