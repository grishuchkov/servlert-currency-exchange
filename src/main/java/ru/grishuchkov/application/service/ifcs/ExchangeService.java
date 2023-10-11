package ru.grishuchkov.application.service.ifcs;

import ru.grishuchkov.application.dto.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeService {

    List<ExchangeRate> getAllRates();

    ExchangeRate getByCurrencyCodes(String codePair);

    ExchangeRate addNewExchangeRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal rate);
}
