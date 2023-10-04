package ru.grishuchkov.application;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

    private String user;
    private String password;
    private String url;
    private String driver;
    private final Properties properties;

    public DatabaseConnectionManager() {
        this.properties = new Properties();
        init();
    }

    private void init(){
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

    public Connection getConnection(){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
