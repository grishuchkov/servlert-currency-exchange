package ru.grishuchkov.application.service;

import ru.grishuchkov.application.dao.ExchangeDaoImpl;
import ru.grishuchkov.application.dao.ifcs.ExchangeDao;
import ru.grishuchkov.application.dto.ExchangeRate;
import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.ExceptionError;
import ru.grishuchkov.application.service.ifcs.ExchangeService;
import ru.grishuchkov.application.utils.Validator;

import java.math.BigDecimal;
import java.util.List;

public class ExchangeServiceImpl implements ExchangeService {
    private final ExchangeDao exchangeDao = new ExchangeDaoImpl();

    @Override
    public List<ExchangeRate> getAllRates() {
        return exchangeDao.findAll();
    }

    @Override
    public ExchangeRate getByCurrencyCodes(String codePair) {

        String baseCurrencyCode = codePair.substring(0, 3);
        String targetCurrencyCode = codePair.substring(3);
        Validator.validateCurrency(baseCurrencyCode);
        Validator.validateCurrency(targetCurrencyCode);

        ExchangeRate rate = exchangeDao.findByCurrencyCodes(baseCurrencyCode, targetCurrencyCode)
                .orElseThrow(() -> new AppException(ExceptionError.EXCHANGE_RATE_NOT_FOUND));

        return rate;
    }

    @Override
    public ExchangeRate addNewExchangeRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal rate) {
        ExchangeRate exchangeRate = exchangeDao.save(baseCurrencyCode, targetCurrencyCode, rate)
                .orElseThrow(() -> new AppException(ExceptionError.EXCHANGE_RATE_NOT_FOUND));

        return exchangeRate;
    }
}
