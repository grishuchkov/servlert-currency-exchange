package ru.grishuchkov.application.exception;

import jakarta.servlet.http.HttpServletResponse;

public class HandleException {

    public static void handle(HttpServletResponse resp, AppException ex){
        String exceptionMessage = ex.getMessage();
    }
}
