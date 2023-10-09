package ru.grishuchkov.application.contextListener;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener(value = "1")
public class DatabaseContextListener implements ServletContextListener {

}
