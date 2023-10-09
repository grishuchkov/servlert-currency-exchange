package ru.grishuchkov.application;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.grishuchkov.application.exception.iternal.DatabaseException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static final Properties properties = new Properties();
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource hikariDataSource;

    static {
        loadProperties();
        config.setJdbcUrl(properties.getProperty("db_url"));
        config.setUsername(properties.getProperty("db_user"));
        config.setPassword(properties.getProperty("db_password"));
        config.setDriverClassName(properties.getProperty("db_driver"));

        hikariDataSource = new HikariDataSource(config);
    }

    public DataSource() {

    }

    private static void loadProperties() {
        try {
            ClassLoader classLoader = DataSource.class.getClassLoader();
            InputStream input = classLoader.getResourceAsStream("database.properties");
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading database.properties");
        }
    }

    public static Connection getConnection() {
        try {
            return hikariDataSource.getConnection();
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

}
