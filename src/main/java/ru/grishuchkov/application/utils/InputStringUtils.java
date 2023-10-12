package ru.grishuchkov.application.utils;

import jakarta.servlet.http.HttpServletRequest;

public class InputStringUtils {

    public static String parseCurrencyPathInfo(HttpServletRequest request) {
        String currencyCode = replaceSlashFromPath(request).toUpperCase();

        return currencyCode;
    }

    public static String parseCodePairFromPathInfo(HttpServletRequest request) {
        String parsedString = replaceSlashFromPath(request).toUpperCase();

        return parsedString;
    }

    private static String replaceSlashFromPath(HttpServletRequest request) {
        return request.getPathInfo().replace("/", "");
    }
}
