package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.models.Model;

import java.util.List;

public interface IService<T extends Model> {

    T create(T obj);

    T update(T obj);

    T getById(long id);

    List<T> getAll();

}
