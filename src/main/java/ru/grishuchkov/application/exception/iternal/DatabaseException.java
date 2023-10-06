package ru.grishuchkov.application.exception.iternal;

import ru.grishuchkov.application.exception.AppException;
import ru.grishuchkov.application.exception.ExceptionError;

public class DatabaseException extends AppException {

    public DatabaseException() {
        super(ExceptionError.INTERNAL_ERROR_IN_DATABASE);
    }
}
