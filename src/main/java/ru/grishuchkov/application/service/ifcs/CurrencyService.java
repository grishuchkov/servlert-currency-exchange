package ru.grishuchkov.application.service.ifcs;

import ru.grishuchkov.application.dto.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getAll();

    Currency getByCode(String currencyCode);

    Currency add(String currencyName, String currencyCode, String currencySign);

}
