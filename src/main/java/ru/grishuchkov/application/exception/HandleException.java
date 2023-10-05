package ru.grishuchkov.application.exception;

import jakarta.servlet.http.HttpServletResponse;
import ru.grishuchkov.application.exception.iternal.DatabaseException;
import ru.grishuchkov.application.exception.iternal.ExceptionDto;
import ru.grishuchkov.application.utils.JsonResponse;

import java.io.IOException;

public class HandleException {

    public static void handle(HttpServletResponse resp, AppException ex) throws IOException {


        if(ex instanceof DatabaseException){
            String message = ex.getMessage();

            ExceptionDto exceptionDto = new ExceptionDto(message, 500);
            JsonResponse.send(resp, exceptionDto, 500);
        }


    }
}
