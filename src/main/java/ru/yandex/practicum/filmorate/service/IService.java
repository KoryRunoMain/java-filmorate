package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.models.Model;

import java.util.List;

public interface IService<T extends Model> {

    List<T> getAll();

    T create(T obj);

    T update(T obj);

    T getId(long id);

}
