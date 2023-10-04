package ru.grishuchkov.application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.grishuchkov.application.service.CurrencySerivceImpl;
import ru.grishuchkov.application.service.ifcs.CurrencyService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/currency")
public class CurrencyServlet extends HttpServlet {

    CurrencyService currencyService = new CurrencySerivceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        writer.write(currencyService.getAllCurrencies());
        writer.close();
    }
}
