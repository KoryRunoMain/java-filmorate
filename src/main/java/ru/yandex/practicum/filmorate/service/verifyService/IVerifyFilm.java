package ru.yandex.practicum.filmorate.service.verifyService;

import ru.yandex.practicum.filmorate.models.Film;

public interface IVerifyFilm {

    void verifyPopularFilmList(int count); // Проверить переданное значение для получения списка популярных фильмов

    void validateFilmFields(Film film); // Проверить поля на корректные переданные данные

    void verifyPassedValuesFilm(long filmId); // Проверить переданные значения фильма

    void verifyPassedValuesUser(long userId); // Проверить переданные значения пользователя

    void verifyBeforeCreateFilm(Film film); // Проверить фильм перед добавлением

    void verifyBeforeUpdateFilm(Film film); // Проверить фильм перед обновлением

    void verifyBeforeDeleteFilm(Film film); // Проверить фильм перед удалением

    boolean isMpaExist(int id); // Проверить есть ли MpaRating с id в БД

    boolean isGenreExist(int id); // Проверить если ли Genre с id в БД

}
