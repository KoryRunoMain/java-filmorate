package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Film;

import java.util.List;

public interface FilmDao {

    Film create(Film film);

    Film update(Film film);

    Film getById(long id);

    Film deleteById(long id);

    List<Film> getPopularFilms(int count);

    List<Film> getAll();

}
