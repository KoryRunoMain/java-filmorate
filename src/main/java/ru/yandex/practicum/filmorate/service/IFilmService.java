package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.models.Film;

import java.util.List;

public interface IFilmService extends IService<Film> {

    Film likeFilm(long filmId, long userId);

    Film removeLike(long filmId, long userId);

    List<Film> getPopularFilms(int count);

}
