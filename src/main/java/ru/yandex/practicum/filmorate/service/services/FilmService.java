package ru.yandex.practicum.filmorate.service.services;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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

    // FILM.Получить список всех фильмов
    @Override
    public List<Film> getAll() {
        return filmDao.getAll();
    }

    // FILM.Создать фильм
    @Override
    public Film create(Film film) {
        // Проверки
        validateFilmFields(film);
        verifyBeforeCreateFilm(film);
        // Создание фильма
        Film newFilm = filmDao.create(film);
        genreDao.addGenreToFilm(newFilm.getId(), film.getGenres());
        newFilm.setGenres(genreDao.getFilmGenres(newFilm.getId()));
        log.info("Фильм успешно создан.");
        return newFilm;
    }

    // FILM.Обновить фильм
    @Override
    public Film update(Film film) {
        // Проверки
        validateFilmFields(film);
        verifyBeforeUpdateFilm(film);
        // Обновление фильма
        Film updatedFilm = filmDao.update(film);
        genreDao.updateFilmGenres(updatedFilm.getId(), film.getGenres());
        updatedFilm.setMpa(mpaDao.getById(Math.toIntExact(updatedFilm.getMpa().getId())));
        updatedFilm.setGenres(genreDao.getFilmGenres(updatedFilm.getId()));
        log.info("Фильм успешно обновлен.");
        return updatedFilm;
    }

    // FILM.Получить фильм по id
    @Override
    public Film getById(long filmId) {
        // Проверка
        verifyPassedValuesFilm(filmId);
        // Получение фильма
        Film film = filmDao.getById(filmId);
        film.setMpa(mpaDao.getById(Math.toIntExact(film.getMpa().getId())));
        film.setGenres(genreDao.getFilmGenres(filmId));
        log.info("Фильм по id: {} получен.", filmId);
        return film;
    }

    // FILM.Поставить лайк фильму
    public void likeFilm(long filmId, long userId) {
        // Проверки
        verifyPassedValuesFilm(filmId);
        verifyPassedValuesUser(userId);
        // Добавление лайка
        likeDao.create(filmId, userId);
        log.info("Поставлен лайк фильму с id: {}", filmId);
    }

    // FILM.Удалить лайк у фильма
    public void removeLike(long filmId, long userId) {
        // Проверки
        verifyPassedValuesFilm(filmId);
        verifyPassedValuesUser(userId);
        // Удаление лайка
        likeDao.delete(filmId, userId);
        log.info("Удлаён лайк у фильма с id: {}", filmId);
    }

    // FILM.Вернуть список из первых count фильмов по количеству лайков
    public List<Film> getPopularFilms(int count) {
        if (count < TOP_FILM_LIST_MINIMAL) {
            log.info("Передан неверный count: {}, count не может быть меньше заданного значения TOP_FILM_LIST_MINIMAL.", count);
            throw new ValidationException(count + " не может быть меньше: " + TOP_FILM_LIST_MINIMAL);
        }
        log.info("Получен список популярных фильмов.");
        return filmDao.getPopularFilms(count);
    }

    // FILM.Проверить поля на корректные переданные данные
    private void validateFilmFields(Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            log.info("Ошибка валидации даты релиза.");
            throw new ValidationException("Ошибка валидации даты релиза. Дата релиза — не раньше 28 декабря 1895 года");
        }
    }

    // VALUES.Проверить переданные значения фильма
    private void verifyPassedValuesFilm(long filmId) {
        if(filmDao.getById(filmId) == null) {
            log.trace("Фильм не найден. Передан null.");
            throw new NotFoundException("Фильм не найден.");
        }
    }

    // VALUES.Проверить переданные значения пользователя
    private void verifyPassedValuesUser(long userId) {
        if (userDao.getById(userId) == null) {
            log.trace("Пользователь не найден. Передан null.");
            throw new NotFoundException("Пользоваетль не найден.");
        }
        if (userId < USER_ID_MINIMAL) {
            log.info("Передан неверный userId: {}, id не может быть меньше заданного значения USER_ID_MINIMAL.", userId);
            throw new NotFoundException(userId + " не может быть меньше: " + USER_ID_MINIMAL);
        }
    }

    // FILM.Проверить фильм перед добавлением
    private void verifyBeforeCreateFilm(Film film) {
        if (filmDao.getAll().contains(film)) {
            log.info("Фильм уже существует id: {}", film.getId());
            throw new NotFoundException("Фильм уже существует.");
        }
        if (!isMpaExist(Math.toIntExact(film.getMpa().getId()))) {
            log.info("Рейтинг не найден mpaId: {}", film.getMpa().getId());
            throw new ValidationException("Рейтинг не найден.");
        }
    }

    // FILM.Проверить фильм перед удалением
    private void verifyBeforeUpdateFilm(Film film) {
        if (!filmDao.getAll().contains(film)) {
            log.info("Фильм отсутствует id: {}", film.getId());
            throw new NotFoundException("Фильм отсутствует.");
        }
    }

    // FILM.Проверить есть ли MpaRating с id в БД
    private boolean isMpaExist(int id) {
        try {
            mpaDao.getById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
