package ru.grishuchkov.application.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException{

    private final ExceptionError exceptionError;

    public AppException(ExceptionError exceptionError) {
        this.exceptionError = exceptionError;
    }
}
