package ru.grishuchkov.application.dao.ifcs;

import ru.grishuchkov.application.dto.CurrencyDto;

import java.util.List;

public interface CurrencyDao {

    List<CurrencyDto> findAllCurrencies();

    CurrencyDto findByCode(String code);
}
