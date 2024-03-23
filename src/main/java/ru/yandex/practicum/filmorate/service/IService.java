package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.models.Model;

import java.util.List;
import java.util.Optional;

public interface IService<T extends Model> {

    T create(T obj);

    T update(T obj);

    T getById(long id);

    List<T> getAll();

}
