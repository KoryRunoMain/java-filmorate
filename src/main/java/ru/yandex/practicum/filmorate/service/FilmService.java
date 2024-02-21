package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;

@Service
public class FilmService implements IService<Film> {

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

    /*
       НОВЫЕ
       МЕТОДЫ
       ТЗ-10
    */
    // FILM.Получить фильм по id
    @Override
    public Film getId(Integer id) {
        return null;
    }

    // FILM.Поставить лайк
    public Film addLike() {
        return null;
    }

    // FILM.Удалить лайк
    public Film deleteLike() {
        return null;
    }

    // FILM.Вернуть список из первых count фильмов по количеству лайков (10)
    public List<Film> getPopularFilms() {
        return null;
    }

}
