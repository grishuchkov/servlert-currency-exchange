package ru.grishuchkov.application.service;

import ru.grishuchkov.application.dao.ExchangeRateDaoImpl;
import ru.grishuchkov.application.dao.ifcs.ExchangeRateDao;
import ru.grishuchkov.application.dto.Exchange;
import ru.grishuchkov.application.dto.ExchangeRate;
import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.ExceptionError;
import ru.grishuchkov.application.service.ifcs.ExchangeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRateDao exchangeRateDao = new ExchangeRateDaoImpl();

    @Override
    public Exchange exchange(String baseCurrencyCode, String targetCurrencyCode, BigDecimal amount) {

        Optional<ExchangeRate> exchangeRate = getExchangeRate(baseCurrencyCode, targetCurrencyCode);

        if (exchangeRate.isPresent()) {
            ExchangeRate exchange = exchangeRate.get();
            BigDecimal convertedAmount = getConvertedAmount(amount, exchange.getRate());

            return new Exchange(exchange.getBaseCurrency(), exchange.getTargetCurrency(),
                    exchange.getRate(), amount, convertedAmount);
        }

        exchangeRate = getExchangeRate(targetCurrencyCode, baseCurrencyCode);

        if (exchangeRate.isPresent()) {
            ExchangeRate exchange = exchangeRate.get();
            BigDecimal rate = getRatioRates(new BigDecimal("1"), exchange.getRate());
            BigDecimal convertedAmount = getConvertedAmount(amount, rate);

            return new Exchange(exchange.getTargetCurrency(), exchange.getBaseCurrency(),
                    rate, amount, convertedAmount);
        }

        Optional<ExchangeRate> baseOptionalRateToUSD = getExchangeRate("USD", baseCurrencyCode);
        Optional<ExchangeRate> targetOptionalRateToUSD = getExchangeRate("USD", targetCurrencyCode);

        if (baseOptionalRateToUSD.isPresent() & targetOptionalRateToUSD.isPresent()) {
            ExchangeRate baseToUSD = baseOptionalRateToUSD.get();
            ExchangeRate targetToUSD = targetOptionalRateToUSD.get();

            BigDecimal rate = getRatioRates(targetToUSD.getRate(), baseToUSD.getRate());
            BigDecimal convertedAmount = getConvertedAmount(amount, rate);

            return new Exchange(baseToUSD.getTargetCurrency(), targetToUSD.getTargetCurrency(),
                    rate, amount, convertedAmount);
        }

        throw new AppException(ExceptionError.CURRENCY_NOT_FOUND);
    }

    private static BigDecimal getConvertedAmount(BigDecimal amount, BigDecimal rate) {
        return rate.multiply(amount);
    }

    private static BigDecimal getRatioRates(BigDecimal divisor, BigDecimal denominator) {
        return divisor.divide(denominator, 6, RoundingMode.HALF_EVEN);
    }

    private Optional<ExchangeRate> getExchangeRate(String baseCurrencyCode, String targetCurrencyCode) {
        return exchangeRateDao.findByCurrencyCodes(baseCurrencyCode, targetCurrencyCode);
    }
}
