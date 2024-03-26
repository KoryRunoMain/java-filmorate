package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Genre;

import java.util.List;
import java.util.Set;

public interface GenreDao {

    Genre getById(int id); // Получить жанр по идентификатору

    List<Genre> getAll(); // Получить список жанров

    void addGenreToFilm(long filmId, Set<Genre> genres); // Добавить жанр фильму

    Set<Genre> getFilmGenres(long filmId); // Получить жанры фильма

    void updateFilmGenres(long filmId, Set<Genre> genres); // Обновить жанры фильма

    void deleteById(long filmId); // Удалить жанры фильма

}
