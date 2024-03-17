package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Film;

import java.sql.SQLException;
import java.util.List;

public interface FilmDao {

    Film createFilm(Film newFilm);

    Film updateFilm(Film film);

    Film deleteFilm(Long id);

    Film getFilmById(Long id);

    List<Film> getAllFilms() throws SQLException;

}
