package ru.grishuchkov.application.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@Getter
public enum ExceptionError {

    INTERNAL_ERROR_IN_DATABASE("Internal application error in database", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    CURRENCY_NOT_FOUND("Currency not found", HttpServletResponse.SC_NOT_FOUND),
    EXCHANGE_RATE_NOT_FOUND("Exchange rate with this currency codes is not found", HttpServletResponse.SC_NOT_FOUND),

    CURRENCY_CODE_IS_NOT_PRESENT("Currency code is not exist in URL", HttpServletResponse.SC_BAD_REQUEST),
    BAD_CURRENCY_CODE("Bad currency code", HttpServletResponse.SC_BAD_REQUEST),
    BAD_CURRENCIES_FIELDS("Bad currencies fields", HttpServletResponse.SC_BAD_REQUEST),
    CURRENCY_ALREADY_EXISTS("Currency with this code already exists", HttpServletResponse.SC_CONFLICT);


    private final String message;
    private final int status;

    ExceptionError(String message, int status) {
        this.message = message;
        this.status = status;
    }

}

