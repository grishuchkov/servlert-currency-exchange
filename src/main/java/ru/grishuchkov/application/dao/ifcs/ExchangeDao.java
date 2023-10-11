package ru.grishuchkov.application.dao.ifcs;

import ru.grishuchkov.application.dto.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExchangeDao {

    List<ExchangeRate> findAll();

    Optional<ExchangeRate> findByCurrencyCodes(String baseCode, String targetCode);

    Optional<ExchangeRate> save(String baseCode, String targetCode, BigDecimal rate);
}
