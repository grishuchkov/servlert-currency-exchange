package ru.grishuchkov.application.utils;

import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.ExceptionError;

public class Validator {

    public static void validateCurrency(String code) {
        if (code.isEmpty()) {
            throw new AppException(ExceptionError.CURRENCY_CODE_IS_NOT_EXIST_IN_URL);
        }
        if (code.length() != 3) {
            throw new AppException(ExceptionError.BAD_CURRENCY_CODE_IN_URL);
        }

    }
}
