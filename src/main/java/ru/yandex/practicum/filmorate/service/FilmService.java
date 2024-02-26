package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    @Override
    public Film getId(long id) {
        return filmStorage.getFilmId(id);
    }

    // FILM.Поставить лайк
    public Film addLike(long filmId, long userId) {
        Film film = filmStorage.getFilmId(filmId);
        if (film == null) {
            throw new FilmNotFoundException(filmId + " не найден");
        }
        if (film.getLikes().contains(userId)) {
            throw new UserNotFoundException(userId + " лайк уже поставлен");
        }
        film.getLikes().add(userId);
        return film;
    }

    // FILM.Удалить лайк
    public Film removeLike(long filmId, long userId) {
        Film film = filmStorage.getFilmId(filmId);
        if (film == null) {
            throw new FilmNotFoundException(filmId + " не найден");
        }
        if (!film.getLikes().contains(userId)) {
            throw new UserNotFoundException(userId + " не найден");
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
