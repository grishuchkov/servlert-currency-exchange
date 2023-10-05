package ru.grishuchkov.application.exception.iternal;

public class DatabaseConnectionException extends DatabaseException {
    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
