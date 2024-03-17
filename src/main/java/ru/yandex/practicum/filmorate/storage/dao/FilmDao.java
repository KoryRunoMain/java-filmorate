package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.IStorageService;

import java.sql.SQLException;
import java.util.List;

public interface FilmDao extends IStorageService<User> {

    Film createFilm(Film newFilm);

    Film updateFilm(Film film);

    Film deleteFilm(Long id);

    Film getFilmById(Long id);

    List<Film> getAllFilms() throws SQLException;

}
