package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.Genre;

import java.util.List;
import java.util.Set;

public interface GenreDao {

    void addGenreToFilm(Film film);

    void deleteGenreFromFilm(Film film);

    Genre getGenreById(Long id);

    Set<Genre> getFilmGenres(Long id);

    List<Genre> getGenres();

}
