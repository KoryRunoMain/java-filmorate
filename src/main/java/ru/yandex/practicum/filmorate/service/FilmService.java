package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;

@Service
public class FilmService implements IService<Film> {

    /*
    Создайте FilmService, который будет отвечать за операции с фильмами,
    — добавление и удаление лайка, вывод 10 наиболее популярных фильмов по количеству лайков.
    Пусть пока каждый пользователь может поставить лайк фильму только один раз.
     */

    private final FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @Override
    public List<Film> getAll() {
        return filmStorage.getFilms();
    }

    @Override
    public Film create(Film newFilm) {
        return filmStorage.createFilm(newFilm);
    }

    @Override
    public Film update(Film film) {
        return filmStorage.updateFilm(film);
    }

    // Добавить новые методы из ТЗ10

}
