package ru.grishuchkov.application.dao;

import ru.grishuchkov.application.DatabaseConnectionManager;
import ru.grishuchkov.application.dao.ifcs.CurrencyDao;
import ru.grishuchkov.application.dto.CurrencyDto;
import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.ExceptionError;
import ru.grishuchkov.application.exception.iternal.DatabaseException;
import ru.grishuchkov.application.utils.CurrencyMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CurrencyDaoImpl implements CurrencyDao {
    private final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager();

    @Override
    public List<CurrencyDto> findAllCurrencies() {
        String SQL = "SELECT * FROM currencies";

        Connection connection = connectionManager.getConnection();
        ResultSet resultSet;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new DatabaseException();
        }

        try {
            return CurrencyMapper.toDtoList(resultSet);
        } catch (SQLException e) {
            throw new AppException(ExceptionError.CURRENCY_NOT_FOUND);
        }
    }
}