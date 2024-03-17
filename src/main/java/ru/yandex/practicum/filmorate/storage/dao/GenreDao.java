package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.IStorageService;

import java.util.List;
import java.util.Set;

public interface GenreDao extends IStorageService<User> {

    void addGenreToFilm(Film film);

    void deleteGenreFromFilm(Film film);

    Genre getGenreById(Long id);

    Set<Genre> getFilmGenres(Long id);

    List<Genre> getGenres();

}
