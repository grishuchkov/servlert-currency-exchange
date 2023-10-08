package ru.grishuchkov.application.dao;

import org.postgresql.util.PSQLException;
import ru.grishuchkov.application.DatabaseConnectionManager;
import ru.grishuchkov.application.dao.ifcs.CurrencyDao;
import ru.grishuchkov.application.dto.Currency;
import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.ExceptionError;
import ru.grishuchkov.application.exception.iternal.DatabaseException;
import ru.grishuchkov.application.utils.CurrencyMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CurrencyDaoImpl implements CurrencyDao {
    private final DatabaseConnectionManager connectionManager = new DatabaseConnectionManager();

    @Override
    public List<Currency> findAllCurrencies() {
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
    public Optional<Currency> findByCode(String code) {
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

    @Override
    public Currency save(Currency currency) {
        String SQL = "INSERT INTO currencies (code, full_name, sign) VALUES (?,?,?)";

        Connection connection = connectionManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, currency.getCode());
            preparedStatement.setString(2, currency.getFullName());
            preparedStatement.setString(3, currency.getSign());

            preparedStatement.executeUpdate();

            ResultSet key = preparedStatement.getGeneratedKeys();

            if (key.next()) {
                long id = key.getLong(1);
                currency.setId(id);
            }

            return currency;

        } catch (PSQLException ex) {
            throw new AppException(ExceptionError.CURRENCY_ALREADY_EXISTS);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

}

