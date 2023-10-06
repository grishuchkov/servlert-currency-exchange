package ru.grishuchkov.application.exception.handler;

import jakarta.servlet.http.HttpServletResponse;
import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.iternal.ExceptionDto;
import ru.grishuchkov.application.utils.JsonResponse;

import java.io.IOException;

public class AppExceptionHandler {

    public static void handle(HttpServletResponse resp, Exception ex) throws IOException {

        if(ex instanceof AppException){
            AppException appException = (AppException) ex;

            String exceptionMessage = appException.getExceptionError().getMessage();
            int exceptionCode = appException.getExceptionError().getStatus();

            JsonResponse.send(resp, new ExceptionDto(exceptionMessage, exceptionCode), exceptionCode);
            return;
        }

        JsonResponse.send(resp, new ExceptionDto(ex.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

    }
}
