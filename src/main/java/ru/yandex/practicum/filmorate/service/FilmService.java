package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;
import java.util.stream.Collectors;

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
    public Film create(Film film) {
        return filmStorage.createFilm(film);
    }

    @Override
    public Film update(Film film) {
        return filmStorage.updateFilm(film);
    }

    @Override
    public Film getId(long id) {
        return filmStorage.getFilmId(id);
    }

    // FILM.Поставить лайк
    public Film addLike(long filmId, long userId) {
        Film film = filmStorage.getFilmId(filmId);
        film.getLikes().add(userId);
        return film;
    }

    // FILM.Удалить лайк
    public Film removeLike(long filmId, long userId) {
        Film film = filmStorage.getFilmId(filmId);
        if (userId < 0) {
            throw new UserNotFoundException(userId + " отрицательное число");
        }
        film.getLikes().remove(userId);
        return film;
    }

    // FILM.Вернуть список из первых count фильмов по количеству лайков (10)
    public List<Film> getPopularFilms(int count) {
        return filmStorage.getFilms().stream()
                .sorted((f1, f2) -> f2.getLikes().size() - f1.getLikes().size())
                .limit(count)
                .collect(Collectors.toList());
    }

}
