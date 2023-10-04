package ru.grishuchkov.application.service;

import com.google.gson.Gson;
import ru.grishuchkov.application.dao.CurrencyDaoImpl;
import ru.grishuchkov.application.dao.ifcs.CurrencyDao;
import ru.grishuchkov.application.service.ifcs.CurrencyService;
import ru.grishuchkov.application.dto.CurrencyDto;

import java.util.List;

public class CurrencySerivceImpl implements CurrencyService {

    private final CurrencyDao currencyDao = new CurrencyDaoImpl();


    @Override
    public String getAllCurrencies() {

        List<CurrencyDto> allCurrencies = currencyDao.findAllCurrencies();

        return new Gson().toJson(allCurrencies);
    }
}
