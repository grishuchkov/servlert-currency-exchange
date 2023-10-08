package ru.grishuchkov.application.dao.ifcs;

import ru.grishuchkov.application.dto.CurrencyDto;

import java.util.List;
import java.util.Optional;

public interface CurrencyDao {

    List<CurrencyDto> findAllCurrencies();

    Optional<CurrencyDto> findByCode(String code);
}
