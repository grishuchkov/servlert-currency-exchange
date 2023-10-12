package ru.grishuchkov.application.utils;

import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.ExceptionError;

public class Validator {

    public static void validateCurrency(String code) {
        if (isFieldEmpty(code)) {
            throw new AppException(ExceptionError.BAD_CURRENCY_CODE);
        }
        if (code.length() != 3) {
            throw new AppException(ExceptionError.BAD_CURRENCY_CODE);
        }
    }


    public static void validateCurrency(String name, String code, String sign) {
        if (isFieldEmpty(name) || isFieldEmpty(code) || isFieldEmpty(sign)) {
            throw new AppException(ExceptionError.BAD_CURRENCIES_FIELDS);
        }
        if (!isCurrencyCodeLengthValid(code)) {
            throw new AppException(ExceptionError.BAD_CURRENCY_CODE);
        }
    }

    public static void exchangeRateValidate(String baseCurrencyCode, String targetCurrencyCode, String rate) {
        if (isFieldEmpty(baseCurrencyCode) || isFieldEmpty(targetCurrencyCode) || isFieldEmpty(rate)) {
            throw new AppException(ExceptionError.BAD_EXCHANGE_RATE_FIELDS);
        }
        if (!isCurrencyCodeLengthValid(baseCurrencyCode) || !isCurrencyCodeLengthValid(targetCurrencyCode)) {
            throw new AppException(ExceptionError.BAD_CURRENCY_CODE);
        }
    }

    public static void validateExchangeRateBody(String requestBody) {
        if (!requestBody.contains("rate=")) {
            throw new AppException(ExceptionError.BAD_EXCHANGE_RATE_FIELDS);
        }
        String rate = requestBody.replace("rate=", "");

        if (!rate.matches("-?\\d+(\\.\\d+)?")) {
            throw new AppException(ExceptionError.BAD_EXCHANGE_RATE_FIELDS);
        }
    }


    private static boolean isFieldEmpty(String field) {
        return field == null || field.isEmpty();
    }

    private static boolean isCurrencyCodeLengthValid(String code) {
        return code.length() == 3;
    }

}
