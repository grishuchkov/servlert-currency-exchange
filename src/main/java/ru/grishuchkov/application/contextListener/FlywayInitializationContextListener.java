package ru.grishuchkov.application.contextListener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;
import ru.grishuchkov.application.DataSource;

@WebListener(value = "1")
public class FlywayInitializationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Flyway flyway = Flyway.configure()
                .dataSource(DataSource.getDataSource())
                .locations("db/migration")
                .load();

        flyway.migrate();
    }

}
