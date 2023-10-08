package ru.grishuchkov.application.dao.ifcs;

import ru.grishuchkov.application.dto.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyDao {

    List<Currency> findAllCurrencies();

    Optional<Currency> findByCode(String code);

    Currency save(Currency currency);
}
