package ru.grishuchkov.application.dao;

import ru.grishuchkov.application.DataSource;
import ru.grishuchkov.application.dao.ifcs.ExchangeDao;
import ru.grishuchkov.application.dto.ExchangeRate;
import ru.grishuchkov.application.exception.iternal.DatabaseException;
import ru.grishuchkov.application.utils.ExchangeRateMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ExchangeDaoImpl implements ExchangeDao {

    @Override
    public List<ExchangeRate> findAll() {

        try (Connection connection = DataSource.getConnection()) {
            String SQL = "SELECT\n" +
                    "er.id,\n" +
                    "c1.id AS base_currency_id,\n" +
                    "c1.code AS base_currency_code,\n" +
                    "c1.sign AS base_currency_sign,\n" +
                    "c1.full_name AS base_currency_name,\n" +
                    "c2.id AS target_currency_id,\n" +
                    "c2.code AS target_currency_code,\n" +
                    "c2.sign as target_currency_sign,\n" +
                    "c2.full_name AS target_currency_name,\n" +
                    "er.rate \n" +
                    "FROM exchange_rates er \n" +
                    "JOIN currencies c1 ON er.base_currency_id = c1.id \n" +
                    "JOIN currencies c2 ON er.target_currency_id = c2.id";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            return ExchangeRateMapper.toList(resultSet);

        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    @Override
    public Optional<ExchangeRate> findByCurrencyCodes(String baseCode, String targetCode) {
        try (Connection connection = DataSource.getConnection()) {
            String SQL = "SELECT\n" +
                    "er.id,\n" +
                    "c1.id AS base_currency_id,\n" +
                    "c1.code AS base_currency_code,\n" +
                    "c1.sign AS base_currency_sign,\n" +
                    "c1.full_name AS base_currency_name,\n" +
                    "c2.id AS target_currency_id,\n" +
                    "c2.code AS target_currency_code,\n" +
                    "c2.sign as target_currency_sign,\n" +
                    "c2.full_name AS target_currency_name,\n" +
                    "er.rate \n" +
                    "FROM exchange_rates er \n" +
                    "JOIN currencies c1 ON er.base_currency_id = c1.id \n" +
                    "JOIN currencies c2 ON er.target_currency_id = c2.id " +
                    "WHERE c1.code = ? AND c2.code = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, baseCode);
            preparedStatement.setString(2, targetCode);

            ResultSet resultSet = preparedStatement.executeQuery();

            return ExchangeRateMapper.toDto(resultSet);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

}
