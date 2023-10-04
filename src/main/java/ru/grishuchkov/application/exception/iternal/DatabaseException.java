package ru.grishuchkov.application.exception.iternal;

import ru.grishuchkov.application.exception.AppException;

public class DatabaseException extends AppException {
    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
