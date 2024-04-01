package ru.yandex.practicum.filmorate.service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.service.IFilmService;
import ru.yandex.practicum.filmorate.service.verifyService.IVerifyFilm;
import ru.yandex.practicum.filmorate.storage.dao.FilmDao;
import ru.yandex.practicum.filmorate.storage.dao.GenreDao;
import ru.yandex.practicum.filmorate.storage.dao.LikeDao;
import ru.yandex.practicum.filmorate.storage.dao.MPADao;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class FilmService implements IFilmService {
    private final FilmDao filmDao;
    private final LikeDao likeDao;
    private final MPADao mpaDao;
    private final GenreDao genreDao;
    private final IVerifyFilm verify;

    @Autowired
    public FilmService(FilmDao filmDao, LikeDao likeDao, MPADao mpaDao, GenreDao genreDao, IVerifyFilm verify) {
        this.filmDao = filmDao;
        this.likeDao = likeDao;
        this.mpaDao = mpaDao;
        this.genreDao = genreDao;
        this.verify = verify;
    }

    // FILM.Получить список всех фильмов
    @Override
    public List<Film> getAll() {
        log.info("Получен список фильмов.");
        return filmDao.getAll();
    }

    // FILM.Создать фильм
    @Override
    public Film create(Film film) {
        // Проверки
        verify.validateFilmFields(film);
        verify.verifyBeforeCreateFilm(film);
        // Создание фильма
        Film newFilm = filmDao.create(film);
        Set<Genre> genres = film.getGenres();
        if (genres != null && !genres.isEmpty()) {
            newFilm.setGenres(genreDao.getFilmGenres(newFilm.getId()));
            log.info("Метод create | Жанры успешно добавлены к фильму genres: {}", genres);
        }
        log.info("Метод create | Фильм успешно создан. film: {}", film);
        return newFilm;
    }

    // FILM.Обновить фильм
    @Override
    public Film update(Film film) {
        // Проверки
        verify.validateFilmFields(film);
        verify.verifyBeforeUpdateFilm(film);
        // Обновление фильма
        Film updatedFilm = filmDao.update(film);
        Set<Genre> genres = updatedFilm.getGenres();
        if (genres != null && !genres.isEmpty()) {
            genreDao.updateFilmGenres(updatedFilm.getId(), genres);
            film.setGenres(genreDao.getFilmGenres(updatedFilm.getId()));
            log.info("Метод create | Жанры успешно добавлены к фильму genres: {}", genres);
        }
        updatedFilm.setMpa(mpaDao.getById(Math.toIntExact(updatedFilm.getMpa().getId())));
        log.info("Метод update | Фильм успешно обновлен.");
        return updatedFilm;
    }

    // FILM.Удалить фильм
    @Override
    public void deleteFilm(long filmId) {
        // Проверка
        verify.verifyBeforeDeleteFilm(filmDao.getById(filmId));
        // Удаление
        filmDao.deleteById(filmId);
        log.info("Фильм удален id: {}", filmDao);
    }

    // FILM.Получить фильм по id
    @Override
    public Film getById(long filmId) {
        // Проверка
        verify.verifyPassedValuesFilm(filmId);
        // Получение фильма
        Film film = filmDao.getById(filmId);
        if (!film.getGenres().isEmpty()) {
            film.setGenres(genreDao.getFilmGenres(filmId));
        } else {
            film.setGenres(Collections.emptySet());
            log.info("Метод getById | Установлены пустые жанры");
        }
        log.info("Метод getById | Установлены жанры genres {}", film.getGenres());
        film.setMpa(mpaDao.getById(Math.toIntExact(film.getMpa().getId())));
        log.info("Метод getById | Фильм по id: {} получен.", film.getId());
        return film;
    }

    // FILM.Поставить лайк фильму
    public void likeFilm(long filmId, long userId) {
        // Проверки
        verify.verifyPassedValuesFilm(filmId);
        verify.verifyPassedValuesUser(userId);
        // Добавление лайка
        likeDao.create(filmId, userId);
        log.info("Метод likeFilm | Поставлен лайк фильму с id: {}", filmId);
    }

    // FILM.Удалить лайк у фильма
    public void removeLike(long filmId, long userId) {
        // Проверки
        verify.verifyPassedValuesFilm(filmId);
        verify.verifyPassedValuesUser(userId);
        // Удаление лайка
        likeDao.delete(filmId, userId);
        log.info("Метод removeLike | Удлаён лайк у фильма с id: {}", filmId);
    }

    // FILM.Вернуть список из первых count фильмов по количеству лайков
    public List<Film> getPopularFilms(int count) {
        // Проверка
        verify.verifyPopularFilmList(count);
        // Получение
        List<Film> films = filmDao.getPopularFilms(count);
        log.info("Метод getPopularFilms | Получен список популярных фильмов.");
        return films;
    }

}
