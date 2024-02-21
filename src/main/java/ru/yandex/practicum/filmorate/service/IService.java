package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.models.IModel;

import java.util.List;

public interface IService <T extends IModel> {

    List<T> getAll();

    T create(T object);

    T update(T object);

}
