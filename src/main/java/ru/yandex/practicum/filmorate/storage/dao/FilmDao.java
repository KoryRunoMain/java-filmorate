package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.Genre;

import java.util.List;
import java.util.Optional;

public interface FilmDao {

    Film create(Film film);

    Film update(Film film);

    Film getById(long id);

    Film deleteById(long id);

    List<Film> getAll();

    void addGenreToFilm(Film film);

    void updateFilmGenres(Film film);

    List<Genre> getFilmGenres(long id);

}
