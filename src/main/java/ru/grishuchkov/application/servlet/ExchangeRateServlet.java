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
import ru.grishuchkov.application.utils.Validator;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {
    private final ExchangeService exchangeService = new ExchangeServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (!method.equals("PATCH")) {
            super.service(req, resp);
            return;
        }

        doPatch(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String codePair = InputStringUtils.parseCodePairFromPathInfo(req);
            JsonResponse.send(resp, exchangeService.getByCurrencyCodes(codePair), HttpServletResponse.SC_OK);
        } catch (AppException ex) {
            AppExceptionHandler.handle(resp, ex);
        }
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String codePair = InputStringUtils.parseCodePairFromPathInfo(req);
        String baseCode = codePair.substring(0, 3);
        String targetCode = codePair.substring(3);

        String bodyParams = req.getReader().readLine();
        String firstBodyParam = bodyParams.split("&")[0];

        try {
            Validator.validateExchangeRateBody(firstBodyParam);

            String rate = firstBodyParam.replace("rate=", "");
            Validator.exchangeRateValidate(baseCode, targetCode, rate);

            JsonResponse.send(resp, exchangeService.updateRate(baseCode, targetCode, new BigDecimal(rate)),
                    HttpServletResponse.SC_OK);

        } catch (AppException ex) {
            AppExceptionHandler.handle(resp, ex);
        }
    }

}
