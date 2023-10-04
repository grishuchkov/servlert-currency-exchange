package ru.grishuchkov.application.contextListener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.grishuchkov.application.DatabaseConnectionManager;

@WebListener(value = "1")
public class DatabaseContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager();
        servletContext.setAttribute("connectionManager", connectionManager);
    }

}
