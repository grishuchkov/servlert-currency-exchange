package ru.grishuchkov.application.dao;

import ru.grishuchkov.application.DatabaseConnectionManager;
import ru.grishuchkov.application.dao.ifcs.CurrencyDao;
import ru.grishuchkov.application.dto.CurrencyDto;
import ru.grishuchkov.application.exception.iternal.DatabaseException;
import ru.grishuchkov.application.utils.CurrencyMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CurrencyDaoImpl implements CurrencyDao {
    private final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager();

    @Override
    public List<CurrencyDto> findAllCurrencies() {
        String SQL = "SELECT * FROM currencies";

        Connection connection = connectionManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            return CurrencyMapper.toDtoList(resultSet);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    @Override
    public Optional<CurrencyDto> findByCode(String code) {
        String SQL = "SELECT * FROM currencies WHERE code = ?";

        Connection connection = connectionManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            return CurrencyMapper.toDto(resultSet);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }
}
