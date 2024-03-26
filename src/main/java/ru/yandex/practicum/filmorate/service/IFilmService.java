package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.models.Film;

import java.util.List;

public interface IFilmService extends IService<Film> {

    void likeFilm(long filmId, long userId); // Поставить лайк фильму

    void removeLike(long filmId, long userId); // Удалить лайк у фильма

    List<Film> getPopularFilms(int count); // Получить список популярных фильмов

    void deleteFilm(long filmId); // Удалить фильм
}
