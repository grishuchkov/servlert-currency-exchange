package ru.grishuchkov.application.dao;

import ru.grishuchkov.application.DataSource;
import ru.grishuchkov.application.dao.ifcs.ExchangeRateDao;
import ru.grishuchkov.application.dto.ExchangeRate;
import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.ExceptionError;
import ru.grishuchkov.application.exception.ExecuteException;
import ru.grishuchkov.application.exception.iternal.DatabaseException;
import ru.grishuchkov.application.utils.ExchangeRateMapper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ExchangeRateDaoImpl implements ExchangeRateDao {

    private final String FIND_ALL_RATES_SQL = "SELECT\n" +
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

    private final String FIND_RATE_BY_CURRENCY_CODES_SQL = "SELECT\n" +
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
            "JOIN currencies c1 ON er.base_currency_id = c1.id AND c1.code = ? \n" +
            "JOIN currencies c2 ON er.target_currency_id = c2.id AND c2.code = ? ";

    private final String INSERT_RATE_BY_CURRENCY_CODES_SQL =
            "INSERT INTO exchange_rates (base_currency_id, target_currency_id, rate)\n" +
                    "VALUES ((SELECT id FROM currencies WHERE code = ?),\n" +
                    "        (SELECT id FROM currencies WHERE code = ?),\n" +
                    "        ?)";

    private final String UPDATE_RATE_SQL = "UPDATE exchange_rates SET rate = ? " +
            "WHERE base_currency_id = (SELECT id FROM currencies WHERE code = ?) " +
            "AND target_currency_id = (SELECT id FROM currencies WHERE code = ?)";

    @Override
    public List<ExchangeRate> findAll() {
        try (Connection connection = DataSource.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_RATES_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            return ExchangeRateMapper.toList(resultSet);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    @Override
    public Optional<ExchangeRate> findByCurrencyCodes(String baseCode, String targetCode) {
        try (Connection connection = DataSource.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RATE_BY_CURRENCY_CODES_SQL);

            preparedStatement.setString(1, baseCode);
            preparedStatement.setString(2, targetCode);

            ResultSet resultSet = preparedStatement.executeQuery();

            return ExchangeRateMapper.toDto(resultSet);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    @Override
    public Optional<ExchangeRate> save(String baseCode, String targetCode, BigDecimal rate) {
        try (Connection connection = DataSource.getConnection()) {

            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            connection.setAutoCommit(false);

            PreparedStatement insertStatement = connection.prepareStatement(INSERT_RATE_BY_CURRENCY_CODES_SQL);
            insertStatement.setString(1, baseCode);
            insertStatement.setString(2, targetCode);
            insertStatement.setBigDecimal(3, rate);

            try {
                insertStatement.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
                throw new ExecuteException();
            }

            PreparedStatement selectStatement = connection.prepareStatement(FIND_RATE_BY_CURRENCY_CODES_SQL);
            selectStatement.setString(1, baseCode);
            selectStatement.setString(2, targetCode);

            ResultSet resultSet = selectStatement.executeQuery();
            connection.commit();

            return ExchangeRateMapper.toDto(resultSet);

        } catch (ExecuteException ex) {
            throw new AppException(ExceptionError.EXCHANGE_RATE_ALREADY_EXISTS);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    @Override
    public Optional<ExchangeRate> update(String baseCode, String targetCode, BigDecimal rate) {
        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RATE_SQL);
            preparedStatement.setBigDecimal(1, rate);
            preparedStatement.setString(2, baseCode);
            preparedStatement.setString(3, targetCode);

            if (preparedStatement.executeUpdate() == 0) {
                throw new ExecuteException();
            }

            return findByCurrencyCodes(baseCode, targetCode);

        } catch (ExecuteException ex) {
            throw new AppException(ExceptionError.CURRENCY_PAIR_IS_MISSING_IN_DATABASE);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

}
