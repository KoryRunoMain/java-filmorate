package ru.yandex.practicum.filmorate.service;

import java.util.List;

public interface IStorageService <T> {

    T create(T create);

    T getElementById(long id);

    T update();

    boolean isContains(long id);

    List<T> getAll();

}
