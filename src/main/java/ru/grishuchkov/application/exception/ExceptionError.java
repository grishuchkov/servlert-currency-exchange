package ru.grishuchkov.application.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@Getter
public enum ExceptionError {

    INTERNAL_ERROR_IN_DATABASE("Internal application error in database", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    CURRENCY_NOT_FOUND("Currency not found", HttpServletResponse.SC_NOT_FOUND);


    private final String message;
    private final int status;

    ExceptionError(String message, int status) {
        this.message = message;
        this.status = status;
    }

}

