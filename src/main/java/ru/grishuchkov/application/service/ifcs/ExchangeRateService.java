package ru.grishuchkov.application.service.ifcs;

import ru.grishuchkov.application.dto.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeRateService {

    List<ExchangeRate> getAllRates();

    ExchangeRate getByCurrencyCodes(String codePair);

    ExchangeRate addNewExchangeRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal rate);

    ExchangeRate updateRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal rate);
}
