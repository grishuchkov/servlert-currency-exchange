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
import ru.grishuchkov.application.utils.Validator;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {

    private final ExchangeService exchangeService = new ExchangeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JsonResponse.send(resp, exchangeService.getAllRates(), HttpServletResponse.SC_OK);
        } catch (AppException ex) {
            AppExceptionHandler.handle(resp, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String baseCode = req.getParameter("baseCurrencyCode");
        String targetCode = req.getParameter("targetCurrencyCode");
        String rateString = req.getParameter("rate");

        try {
            Validator.exchangeRateValidate(baseCode, targetCode, rateString);

            JsonResponse.send(resp, exchangeService.addNewExchangeRate(baseCode, targetCode,
                                                    new BigDecimal(rateString)), HttpServletResponse.SC_OK);
        } catch (AppException ex) {
            AppExceptionHandler.handle(resp, ex);
        }
    }
}
