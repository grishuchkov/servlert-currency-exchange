package ru.grishuchkov.application.dao.ifcs;

import ru.grishuchkov.application.dto.ExchangeRate;

import java.util.List;

public interface ExchangeDao {

    List<ExchangeRate> findAll();
}
