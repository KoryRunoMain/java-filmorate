package ru.yandex.practicum.filmorate.exceptions;

public class ValidationException extends RuntimeException {

    private final int statusCode;

    public ValidationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
