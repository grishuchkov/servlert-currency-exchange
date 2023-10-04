package ru.grishuchkov.application.utils;

import ru.grishuchkov.application.dto.CurrencyDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyMapper {

    public static CurrencyDto toDto(ResultSet resultSet) throws SQLException {
        CurrencyDto dto = new CurrencyDto();

        if(resultSet.next()){
            dto.setId(resultSet.getLong("id"));
            dto.setCode(resultSet.getString("code"));
            dto.setFullName(resultSet.getString("full_name"));
            dto.setSign(resultSet.getString("sign"));
        }

        return dto;
    }

    public static List<CurrencyDto> toDtoList(ResultSet resultSet) throws SQLException {
        List<CurrencyDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
                CurrencyDto dto = new CurrencyDto();
                dto.setId(resultSet.getLong("id"));
                dto.setCode(resultSet.getString("code"));
                dto.setFullName(resultSet.getString("full_name"));
                dto.setSign(resultSet.getString("sign"));
                dtoList.add(dto);
        }
        return dtoList;
    }
}
