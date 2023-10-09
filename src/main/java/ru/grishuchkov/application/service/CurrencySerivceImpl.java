package ru.grishuchkov.application.service;

import ru.grishuchkov.application.dao.CurrencyDaoImpl;
import ru.grishuchkov.application.dao.ifcs.CurrencyDao;
import ru.grishuchkov.application.dto.Currency;
import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.ExceptionError;
import ru.grishuchkov.application.service.ifcs.CurrencyService;

import java.util.List;
import java.util.Optional;

public class CurrencySerivceImpl implements CurrencyService {

    private final CurrencyDao currencyDao = new CurrencyDaoImpl();

    @Override
    public List<Currency> getAllCurrencies() {

        return currencyDao.findAllCurrencies();
    }

    @Override
    public Currency getByCode(String code) {
        Optional<Currency> currency = currencyDao.findByCode(code);

        return currency.orElseThrow(
                () -> new AppException(ExceptionError.CURRENCY_NOT_FOUND));
    }

    @Override
    public Currency add(String currencyName, String currencyCode, String currencySign) {
        Currency currency = new Currency();
        currency.setFullName(currencyName);
        currency.setCode(currencyCode);
        currency.setSign(currencySign);

        return currencyDao.save(currency);
    }
}
