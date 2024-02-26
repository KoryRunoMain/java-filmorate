package ru.yandex.practicum.filmorate.controllers;

import java.util.List;

public interface IController<T> {

    List<T> getAll();

    T create(T obj);

    T update(T obj);

    T getId(Long obj);

}