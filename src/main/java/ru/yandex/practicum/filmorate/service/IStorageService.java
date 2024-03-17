package ru.yandex.practicum.filmorate.service;

import java.util.List;
import java.util.Optional;

public interface IStorageService <T> {

    T create(T object);

    T update(T object);

    Optional<T> get(long id);

    Optional<T> delete(long id);

    List<T> getAll();

    boolean isElementContains(long id);

}
