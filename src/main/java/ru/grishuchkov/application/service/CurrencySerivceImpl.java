package ru.grishuchkov.application.service;

import ru.grishuchkov.application.dao.CurrencyDaoImpl;
import ru.grishuchkov.application.dao.ifcs.CurrencyDao;
import ru.grishuchkov.application.dto.CurrencyDto;
import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.ExceptionError;
import ru.grishuchkov.application.service.ifcs.CurrencyService;

import java.util.List;
import java.util.Optional;

public class CurrencySerivceImpl implements CurrencyService {

    private final CurrencyDao currencyDao = new CurrencyDaoImpl();

    @Override
    public List<CurrencyDto> getAllCurrencies() {

        List<CurrencyDto> allCurrencies = currencyDao.findAllCurrencies();
        return allCurrencies;
    }

    @Override
    public CurrencyDto getByCode(String code) {
        Optional<CurrencyDto> currency = currencyDao.findByCode(code);

        return currency.orElseThrow(
                () -> new AppException(ExceptionError.CURRENCY_NOT_FOUND));
    }
}
