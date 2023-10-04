package ru.grishuchkov.application.exception.iternal;

import ru.grishuchkov.application.exception.AppException;

public class DatabaseConnectionException extends AppException {
    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
