package ru.yandex.practicum.filmorate.service.verifyService.verifies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.service.verifyService.IVerifyFilm;
import ru.yandex.practicum.filmorate.storage.dao.*;

import java.time.LocalDate;

@Slf4j
@Service
public class VerifyFilm implements IVerifyFilm {
    private static final long USER_ID_MINIMAL = 1L;
    private static final int TOP_FILM_LIST_MINIMAL = 1;
    private final FilmDao filmDao;
    private final MPADao mpaDao;
    private final UserDao userDao;
    private final GenreDao genreDao;

    @Autowired
    public VerifyFilm(FilmDao filmDao, LikeDao likeDao, MPADao mpaDao, GenreDao genreDao, UserDao userDao) {
        this.filmDao = filmDao;
        this.mpaDao = mpaDao;
        this.userDao = userDao;
        this.genreDao = genreDao;
    }

    // FILM.Проверить переданное значение для получения списка популярных фильмов
    @Override
    public void verifyPopularFilmList(int count) {
        if (count < TOP_FILM_LIST_MINIMAL) {
            log.info("Передан неверный count: {}, count не может быть меньше заданного значения TOP_FILM_LIST_MINIMAL.", count);
            throw new ValidationException(count + " не может быть меньше: " + TOP_FILM_LIST_MINIMAL);
        }
    }

    // FILM.Проверить поля на корректные переданные данные
    @Override
    public void validateFilmFields(Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            log.info("Ошибка валидации даты релиза.");
            throw new ValidationException("Ошибка валидации даты релиза. Дата релиза — не раньше 28 декабря 1895 года");
        }
    }

    // VALUES.Проверить переданные значения фильма
    @Override
    public void verifyPassedValuesFilm(long filmId) {
        if (filmDao.getById(filmId) == null) {
            log.trace("Фильм не найден. Передан null.");
            throw new NotFoundException("Фильм не найден.");
        }
    }

    // VALUES.Проверить переданные значения пользователя
    @Override
    public void verifyPassedValuesUser(long userId) {
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
    @Override
    public void verifyBeforeCreateFilm(Film film) {
        if (filmDao.getAll().contains(film)) {
            log.info("Фильм уже существует id: {}", film.getId());
            throw new NotFoundException("Фильм уже существует.");
        }
        if (!isMpaExist(Math.toIntExact(film.getMpa().getId()))) {
            log.info("Рейтинг не найден mpaId: {}", film.getMpa().getId());
            throw new ValidationException("Рейтинг не найден.");
        }
        for (Genre genre : film.getGenres()) {
            if (!isGenreExist(Math.toIntExact(genre.getId()))) {
                log.info("Жанр не найден genreId: {}", genre.getId());
                throw new ValidationException("Жанр не найден.");
            }
        }
    }

    // FILM.Проверить фильм перед обновлением
    @Override
    public void verifyBeforeUpdateFilm(Film film) {
        if (!filmDao.getAll().contains(film)) {
            log.info("Фильм отсутствует id: {}", film.getId());
            throw new NotFoundException("Фильм отсутствует.");
        }
        for (Genre genre : film.getGenres()) {
            if (!isGenreExist(Math.toIntExact(genre.getId()))) {
                log.info("Жанр не найден genreId: {}", genre.getId());
                throw new ValidationException("Жанр не найден.");
            }
        }
    }

    // FILM.Проверить фильм перед удалением
    @Override
    public void verifyBeforeDeleteFilm(Film film) {
        if (!filmDao.getAll().contains(film)) {
            log.info("Фильм отсутствует id: {}", film.getId());
            throw new NotFoundException("Фильм отсутствует.");
        }
    }

    // FILM.Проверить есть ли MpaRating с id в БД
    @Override
    public boolean isMpaExist(int id) {
        try {
            mpaDao.getById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    // GENRE.Проверить есть ли Genre с id в БД
    @Override
    public boolean isGenreExist(int id) {
        try {
            genreDao.getById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
