package ru.grishuchkov.application.utils;

import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.ExceptionError;

public class Validator {

    public static void validateCurrency(String code) {
        if (code.isEmpty()) {
            throw new AppException(ExceptionError.BAD_CURRENCY_CODE);
        }
        if (code.length() != 3) {
            throw new AppException(ExceptionError.BAD_CURRENCY_CODE);
        }
    }

    public static void validateCurrency(String name, String code, String sign) {
        if (name == null || name.isEmpty() || code == null || code.isEmpty() || sign == null || sign.isEmpty()) {
            throw new AppException(ExceptionError.BAD_CURRENCIES_FIELDS);
        }
        if (code.length() != 3) {
            throw new AppException(ExceptionError.BAD_CURRENCY_CODE);
        }
    }

}
