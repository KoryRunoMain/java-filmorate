package ru.yandex.practicum.filmorate.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.IFilmService;
import ru.yandex.practicum.filmorate.storage.dao.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class FilmService implements IFilmService {
    private static final long USER_ID_MINIMAL = 1L;
    private static final int TOP_FILM_LIST_MINIMAL = 1;
    private final FilmDao filmDao;
    private final LikeDao likeDao;
    private final MPADao mpaDao;
    private final GenreDao genreDao;
    private final UserDao userDao;

    @Autowired
    public FilmService(FilmDao filmDao, LikeDao likeDao, MPADao mpaDao, GenreDao genreDao, UserDao userDao) {
        this.filmDao = filmDao;
        this.likeDao = likeDao;
        this.mpaDao = mpaDao;
        this.genreDao = genreDao;
        this.userDao = userDao;
    }

    @Override
    public List<Film> getAll() {
        return filmDao.getAll();
    }

    @Override
    public Film create(Film film) {
        validateCreateFilm(film);
        Film newFilm = filmDao.create(film);
        genreDao.addGenreToFilm(newFilm.getId(), film.getGenres());
        newFilm.setGenres(genreDao.getFilmGenres(newFilm.getId()));
        return newFilm;
    }

    @Override
    public Film update(Film film) {
        validateUpdateFilm(film);
        Film updatedFilm = filmDao.update(film);
        genreDao.updateFilmGenres(updatedFilm.getId(), film.getGenres());
        updatedFilm.setGenres(genreDao.getFilmGenres(updatedFilm.getId()));
        updatedFilm.setMpa(mpaDao.getById(Math.toIntExact(updatedFilm.getMpa().getId())));
        return updatedFilm;
    }

    @Override
    public Film getById(long id) {
        Film film = filmDao.getById(id);
        if (film == null) {
            throw new NotFoundException("Фильм не найден.");
        }
        film.setGenres(genreDao.getFilmGenres(id));
        film.setMpa(mpaDao.getById(Math.toIntExact(film.getMpa().getId())));
        return film;
    }

    // FILM.Поставить лайк
    public Film likeFilm(long filmId, long userId) {
        Film film = filmDao.getById(filmId);
        User user = userDao.getById(userId);
        if(film == null) {
            throw new NotFoundException("Фильм не найден.");
        }
        if (user == null) {
            throw new NotFoundException("Пользоваетль не найден.");
        }
        likeDao.create(filmId, userId);
        return film;
    }

    // FILM.Удалить лайк
    public Film removeLike(long filmId, long userId) {
        Film film = filmDao.getById(filmId);
        User user = userDao.getById(userId);
        if(film == null) {
            throw new NotFoundException("Фильм не найден.");
        }
        if (user == null) {
            throw new NotFoundException("Пользоваетль не найден.");
        }
        if (userId < USER_ID_MINIMAL) {
            throw new NotFoundException(userId + " не может быть меньше: " + USER_ID_MINIMAL);
        }
        likeDao.delete(filmId, userId);
        return film;
    }

    // FILM.Вернуть список из первых count фильмов по количеству лайков (10)
    public List<Film> getPopularFilms(int count) {
        if (count < TOP_FILM_LIST_MINIMAL) {
            throw new ValidationException(count + " не может быть меньше: " + TOP_FILM_LIST_MINIMAL);
        }
        return filmDao.getPopularFilms(count);
    }

    private void validateCreateFilm(Film film) {
        if (filmDao.getAll().contains(film)) {
            throw new NotFoundException("Фильм уже существует.");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Ошибка валидации даты релиза. Дата релиза — не раньше 28 декабря 1895 года");
        }
        if (!mpaExist(Math.toIntExact(film.getMpa().getId()))) {
            throw new ValidationException("Рейтинг не найден.");
        }
    }

    private void validateUpdateFilm(Film film) {
        if (!filmDao.getAll().contains(film)) {
            throw new NotFoundException("Фильм отсутствует.");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Ошибка валидации даты релиза. Дата релиза — не раньше 28 декабря 1895 года");
        }
    }

    private boolean mpaExist(int id) {
        try {
            mpaDao.getById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
