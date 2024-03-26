package ru.yandex.practicum.filmorate.service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.OperationNotFoundException;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.service.IGenreService;
import ru.yandex.practicum.filmorate.service.verifyService.IVerifyGenre;
import ru.yandex.practicum.filmorate.storage.dao.GenreDao;

import java.util.List;

@Slf4j
@Service
public class GenreService implements IGenreService {
    private final GenreDao genreDao;
    private final IVerifyGenre verify;

    @Autowired
    public GenreService(GenreDao genreDao, IVerifyGenre verify) {
        this.genreDao = genreDao;
        this.verify = verify;
    }

    // GENRE.Создать жанр
    @Override
    public Genre create(Genre genre) {
        log.info("Не удалось добавить жанр. Добавить можно в файле data.sql");
        throw new OperationNotFoundException("Добавить жанр можно в файле data.sql");
    }

    // GENRE.Обновить жанр
    @Override
    public Genre update(Genre genre) {
        log.info("Не удалось обновить жанр. Обновить можно в файле data.sql");
        throw new OperationNotFoundException("Обновить жанр можно в файле data.sql");
    }

    // GENRE.Получить жанр по id
    @Override
    public Genre getById(long id) {
        // Проверка
        verify.verifyGenre((int) id);
        // Получение
        Genre genre = genreDao.getById((int) id);
        log.info("Получен жанр с id: {}", id);
        return genre;
    }

    // GENRE.Получить список жанров
    @Override
    public List<Genre> getAll() {
        List<Genre> genres = genreDao.getAll();
        log.info("Получен список жанров");
        return genres;
    }

}
