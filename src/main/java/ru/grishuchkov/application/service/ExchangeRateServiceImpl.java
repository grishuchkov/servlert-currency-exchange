package ru.grishuchkov.application.service;

import ru.grishuchkov.application.dao.ExchangeRateDaoImpl;
import ru.grishuchkov.application.dao.ifcs.ExchangeRateDao;
import ru.grishuchkov.application.dto.ExchangeRate;
import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.ExceptionError;
import ru.grishuchkov.application.service.ifcs.ExchangeRateService;
import ru.grishuchkov.application.utils.Validator;

import java.math.BigDecimal;
import java.util.List;

public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final ExchangeRateDao exchangeRateDao = new ExchangeRateDaoImpl();

    @Override
    public List<ExchangeRate> getAllRates() {
        return exchangeRateDao.findAll();
    }

    @Override
    public ExchangeRate getByCurrencyCodes(String codePair) {

        String baseCurrencyCode = codePair.substring(0, 3);
        String targetCurrencyCode = codePair.substring(3);
        Validator.validateCurrency(baseCurrencyCode);
        Validator.validateCurrency(targetCurrencyCode);

        ExchangeRate rate = exchangeRateDao.findByCurrencyCodes(baseCurrencyCode, targetCurrencyCode)
                .orElseThrow(() -> new AppException(ExceptionError.EXCHANGE_RATE_NOT_FOUND));

        return rate;
    }

    @Override
    public ExchangeRate addNewExchangeRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal rate) {
        ExchangeRate exchangeRate = exchangeRateDao.save(baseCurrencyCode, targetCurrencyCode, rate)
                .orElseThrow(() -> new AppException(ExceptionError.EXCHANGE_RATE_NOT_FOUND));

        return exchangeRate;
    }

    @Override
    public ExchangeRate updateRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal rate) {
        ExchangeRate exchangeRate = exchangeRateDao.update(baseCurrencyCode, targetCurrencyCode, rate).get();

        return exchangeRate;
    }
}
