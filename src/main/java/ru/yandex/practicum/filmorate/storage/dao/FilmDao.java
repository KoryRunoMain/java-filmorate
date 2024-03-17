package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.service.IStorageService;

import java.util.Set;

public interface FilmDao extends IStorageService<Film> {

    void createGenre(long filmId, Set<Genre> genres);

    void updateGenre(long filmId, Set<Genre> genres);

    void getAllGenres(long id);

}
