package ru.grishuchkov.application.service;

import ru.grishuchkov.application.dao.ExchangeDaoImpl;
import ru.grishuchkov.application.dao.ifcs.ExchangeDao;
import ru.grishuchkov.application.dto.ExchangeRate;
import ru.grishuchkov.application.service.ifcs.ExchangeService;

import java.util.List;

public class ExchangeServiceImpl implements ExchangeService {
    private final ExchangeDao exchangeDao = new ExchangeDaoImpl();
    @Override
    public List<ExchangeRate> getAllRates() {
        return exchangeDao.findAll();
    }
}
