package ru.grishuchkov.application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.handler.AppExceptionHandler;
import ru.grishuchkov.application.service.ExchangeServiceImpl;
import ru.grishuchkov.application.service.ifcs.ExchangeService;
import ru.grishuchkov.application.utils.InputStringUtils;
import ru.grishuchkov.application.utils.JsonResponse;

import java.io.IOException;

@WebServlet("/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {
    private final ExchangeService exchangeService = new ExchangeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String codePair = InputStringUtils.parseExchangePathInfo(req);
            JsonResponse.send(resp, exchangeService.getByCurrencyCodes(codePair), HttpServletResponse.SC_OK);
        } catch (AppException ex) {
            AppExceptionHandler.handle(resp, ex);
        }

    }
}
