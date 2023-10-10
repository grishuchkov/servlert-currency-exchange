package ru.grishuchkov.application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.grishuchkov.application.exception.handler.AppExceptionHandler;
import ru.grishuchkov.application.service.CurrencySerivceImpl;
import ru.grishuchkov.application.service.ifcs.CurrencyService;
import ru.grishuchkov.application.utils.InputStringUtils;
import ru.grishuchkov.application.utils.JsonResponse;
import ru.grishuchkov.application.utils.Validator;

import java.io.IOException;

@WebServlet("/currency/*")
public class CurrencyServlet extends HttpServlet {

    private final CurrencyService currencyService = new CurrencySerivceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = InputStringUtils.parseCurrencyPathInfo(req);

        try {
            Validator.validateCurrency(code);
            JsonResponse.send(resp, currencyService.getByCode(code), HttpServletResponse.SC_OK);
        } catch (Exception e) {
            AppExceptionHandler.handle(resp, e);
        }

    }
}
