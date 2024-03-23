package ru.yandex.practicum.filmorate.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.service.IGenreService;
import ru.yandex.practicum.filmorate.storage.dao.GenreDao;

import java.util.List;

@Service
public class GenreService implements IGenreService {
    private final GenreDao genreDao;

    @Autowired
    public GenreService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre create(Genre genre) {
        throw new UnsupportedOperationException("Добавить жанр можно в файле data.sql");
    }

    @Override
    public Genre update(Genre genre) {
        throw new UnsupportedOperationException("Обновить жанр можно в файле data.sql");
    }

    @Override
    public Genre getById(long id) {
        genreExist((int) id);
        return genreDao.getById((int) id);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    private void genreExist(int genreId) {
        try {
            genreDao.getById(genreId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Жанр c id " + genreId + " не найден.");
        }
    }

}
