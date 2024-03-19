package ru.yandex.practicum.filmorate.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.service.IFilmService;
import ru.yandex.practicum.filmorate.service.IService;
import ru.yandex.practicum.filmorate.storage.inMemoryStorage.FilmStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService implements IFilmService {
    private static final long USER_ID_MINIMAL = 1L;
    private static final int TOP_FILM_LIST_MINIMAL = 1;
    private final FilmStorage filmStorage;

    @Autowired
    public FilmService(@Qualifier("FilmDao") FilmStorage filmStorage) {
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
    public Film getById(long id) {
        return filmStorage.getFilmId(id);
    }

    // FILM.Поставить лайк
    public Film likeFilm(long filmId, long userId) {
        Film film = filmStorage.getFilmId(filmId);
        film.addLike(userId);
        return film;
    }

    // FILM.Удалить лайк
    public Film removeLike(long filmId, long userId) {
        Film film = filmStorage.getFilmId(filmId);
        if (userId < USER_ID_MINIMAL) {
            throw new NotFoundException(userId + " не может быть меньше: " + USER_ID_MINIMAL);
        }
        film.deleteLike(userId);
        return film;
    }

    // FILM.Вернуть список из первых count фильмов по количеству лайков (10)
    public List<Film> getPopularFilms(int count) {
        if (count < TOP_FILM_LIST_MINIMAL) {
            throw new ValidationException(count + " не может быть меньше: " + TOP_FILM_LIST_MINIMAL);
        }
        return filmStorage.getFilms().stream()
                .sorted((f1, f2) -> f2.getLikes().size() - f1.getLikes().size())
                .limit(count)
                .collect(Collectors.toList());
    }

}
