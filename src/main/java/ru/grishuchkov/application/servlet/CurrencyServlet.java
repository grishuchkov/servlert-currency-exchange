package ru.grishuchkov.application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.HandleException;
import ru.grishuchkov.application.service.CurrencySerivceImpl;
import ru.grishuchkov.application.service.ifcs.CurrencyService;
import ru.grishuchkov.application.utils.JsonResponse;

import java.io.IOException;

@WebServlet("/currency")
public class CurrencyServlet extends HttpServlet {

    CurrencyService currencyService = new CurrencySerivceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
           JsonResponse.send(resp, currencyService.getAllCurrencies(), HttpServletResponse.SC_OK);
        }catch (AppException exception){
            HandleException.handle(resp, exception);
        }
    }
}
