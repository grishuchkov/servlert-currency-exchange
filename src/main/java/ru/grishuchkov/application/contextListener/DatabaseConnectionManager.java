package ru.grishuchkov.application.contextListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private final String user;
    private final String password;
    private final String url;
    private final String driver;


    public DatabaseConnectionManager(String user, String password, String url, String driver) {
        this.user = user;
        this.password = password;
        this.url = url;
        this.driver = driver;
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
