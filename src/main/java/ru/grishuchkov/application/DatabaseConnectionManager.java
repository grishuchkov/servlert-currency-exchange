package ru.grishuchkov.application;

import ru.grishuchkov.application.exception.iternal.DatabaseException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

    private final Properties properties;
    private String user;
    private String password;
    private String url;
    private String driver;

    public DatabaseConnectionManager() {
        this.properties = new Properties();
        init();
    }

    private void init() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream input = classLoader.getResourceAsStream("database.properties");
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading database.properties");
        }

        this.user = properties.getProperty("db_user");
        this.password = properties.getProperty("db_password");
        this.url = properties.getProperty("db_url");
        this.driver = properties.getProperty("db_driver");
    }

    public Connection getConnection() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DatabaseException();
        }
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

}
