package ru.grishuchkov.application.service.ifcs;

import ru.grishuchkov.application.dto.Exchange;

import java.math.BigDecimal;

public interface ExchangeService {
    Exchange exchange(String baseCurrencyCode, String targetCurrencyCode, BigDecimal amount);
}
