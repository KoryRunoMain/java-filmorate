package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    Genre create(Genre genre);

    Genre update(Genre genre);

    Genre getById(long id);

    Genre deleteById(long id);

    List<Genre> getAll();

}
