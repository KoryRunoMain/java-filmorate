package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Film;

import java.util.List;

public interface FilmDao {

    Film create(Film film); // Создать фильм

    Film update(Film film); // Обновить фильм

    Film getById(long id); // Получить фильм по идентификатору

    Film deleteById(long id); // Удалить фильм по идентификатору

    List<Film> getPopularFilms(int count); // Получить список популярных фильмов по count (count default = 10)

    List<Film> getAll(); // Получить список фильмов

}
