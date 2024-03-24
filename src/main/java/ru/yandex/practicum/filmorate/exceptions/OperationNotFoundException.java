package ru.yandex.practicum.filmorate.exceptions;

public class OperationNotFoundException extends UnsupportedOperationException {
    public OperationNotFoundException(String message) {
        super(message);
    }
}
