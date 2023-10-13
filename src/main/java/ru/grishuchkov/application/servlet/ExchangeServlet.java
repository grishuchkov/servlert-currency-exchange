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
import ru.grishuchkov.application.utils.JsonResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/exchange")
public class ExchangeServlet extends HttpServlet {

    private final ExchangeService exchangeService = new ExchangeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String baseCurrencyCode = req.getParameter("from");
        String targetCurrencyCode = req.getParameter("to");
        String amount = req.getParameter("amount");

        //todo Validation;

        try {
            JsonResponse.send(resp, exchangeService.exchange(baseCurrencyCode, targetCurrencyCode,
                                                new BigDecimal(amount)), HttpServletResponse.SC_OK);
        }catch (AppException exception){
            AppExceptionHandler.handle(resp, exception);
        }
    }
}

