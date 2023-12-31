package ru.grishuchkov.application.utils;

import ru.grishuchkov.application.dto.Currency;
import ru.grishuchkov.application.dto.ExchangeRate;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExchangeRateMapper {

    public static List<ExchangeRate> toList(ResultSet resultSet) throws SQLException {
        List<ExchangeRate> rateList = new ArrayList<>();

        while (resultSet.next()) {
            ExchangeRate exchangeRate = buildExchangeRate(resultSet);
            rateList.add(exchangeRate);
        }

        return rateList;
    }


    public static Optional<ExchangeRate> toDto(ResultSet resultSet) throws SQLException {
        ExchangeRate exchangeRate = null;

        if (resultSet.next()) {
            exchangeRate = buildExchangeRate(resultSet);
        }

        return Optional.ofNullable(exchangeRate);
    }

    private static ExchangeRate buildExchangeRate(ResultSet resultSet) throws SQLException {
        Currency baseCurrency = new Currency();
        baseCurrency.setId(resultSet.getLong("base_currency_id"));
        baseCurrency.setName(resultSet.getString("base_currency_name"));
        baseCurrency.setCode(resultSet.getString("base_currency_code"));
        baseCurrency.setSign(resultSet.getString("base_currency_sign"));

        Currency targetCurrency = new Currency();
        targetCurrency.setId(resultSet.getLong("target_currency_id"));
        targetCurrency.setName(resultSet.getString("target_currency_name"));
        targetCurrency.setCode(resultSet.getString("target_currency_code"));
        targetCurrency.setSign(resultSet.getString("target_currency_sign"));

        BigDecimal rate = resultSet.getBigDecimal("rate");
        Long id = resultSet.getLong("id");

        return new ExchangeRate(id, baseCurrency, targetCurrency, rate);
    }
}
