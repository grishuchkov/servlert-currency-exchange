package ru.grishuchkov.application.service.ifcs;

import ru.grishuchkov.application.dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> getAllCurrencies();
}
