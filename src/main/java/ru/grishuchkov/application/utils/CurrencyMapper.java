package ru.grishuchkov.application.utils;

import ru.grishuchkov.application.dto.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyMapper {

    public static Optional<Currency> toDto(ResultSet resultSet) throws SQLException {
        Currency dto = null;

        if (resultSet.next()) {
            dto = new Currency();
            dto.setId(resultSet.getLong("id"));
            dto.setCode(resultSet.getString("code"));
            dto.setName(resultSet.getString("full_name"));
            dto.setSign(resultSet.getString("sign"));
        }
        return Optional.ofNullable(dto);
    }

    public static List<Currency> toDtoList(ResultSet resultSet) throws SQLException {
        List<Currency> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            Currency dto = new Currency();
            dto.setId(resultSet.getLong("id"));
            dto.setCode(resultSet.getString("code"));
            dto.setName(resultSet.getString("full_name"));
            dto.setSign(resultSet.getString("sign"));
            dtoList.add(dto);
        }
        return dtoList;
    }
}
