package ru.grishuchkov.application.contextListener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.IOException;
import java.util.Properties;

@WebListener(value = "1")
public class DatabaseContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();
        ServletContext servletContext = sce.getServletContext();

        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF/classes/database.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Error loading database.properties");
        }

        String user = properties.getProperty("db_user");
        String password = properties.getProperty("db_password");
        String url = properties.getProperty("db_url");
        String driver = properties.getProperty("db_driver");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading DB driver");
        }

        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(user, password, url, driver);
        servletContext.setAttribute("connectionManager", connectionManager);
    }

}
