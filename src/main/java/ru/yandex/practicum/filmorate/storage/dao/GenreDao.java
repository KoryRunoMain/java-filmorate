package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    Genre create(Genre genre);

    Optional<Genre> get(long id);

    Optional<Genre> delete(long id);

    List<Genre> getAll();

    boolean isElementContains(long id);

}
