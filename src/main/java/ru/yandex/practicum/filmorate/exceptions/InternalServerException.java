package ru.yandex.practicum.filmorate.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalServerException extends Throwable {
    public InternalServerException (String message) {
        super(message);
        log.error(message);
    }
}
