package ru.grishuchkov.application.exception.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.grishuchkov.application.exception.iternal.ExceptionDto;
import ru.grishuchkov.application.utils.JsonResponse;

import java.io.IOException;

@WebServlet(name = "errorHandlerServlet", urlPatterns = {"/errorHandler"}, loadOnStartup = 1)
public class TomcatExceptionHandler extends HttpServlet {

    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Throwable exception = (Throwable) request.getAttribute("jakarta.servlet.error.exception");
        String errorMessage = (String) request.getAttribute("jakarta.servlet.error.message");
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");

        if (exception != null){
            errorMessage = exception.getCause().toString();
        }

        JsonResponse.send(response, new ExceptionDto(errorMessage, statusCode), statusCode);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }
}
