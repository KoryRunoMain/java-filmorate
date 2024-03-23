package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Genre;

import java.util.List;
import java.util.Set;

public interface GenreDao {

    Genre getById(int id);

    List<Genre> getAll();

    void addGenreToFilm(long filmId, Set<Genre> genres);

    void updateFilmGenres(long filmId, Set<Genre> genres);

    Set<Genre> getFilmGenres(long filmId);

    void deleteById(long filmId);

}
