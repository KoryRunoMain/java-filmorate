package ru.yandex.practicum.filmorate.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.service.IGenreService;
import ru.yandex.practicum.filmorate.service.IService;
import ru.yandex.practicum.filmorate.storage.dao.GenreDao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService implements IGenreService {
    private final GenreDao genreDao;

    @Autowired
    public GenreService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre getById(long id) {
        return genreDao.getById(id);
    }

    public List<Genre> getAll() {
        return genreDao.getAll().stream()
                .sorted(Comparator.comparing(Genre::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Genre create(Genre genre) {
        throw new UnsupportedOperationException("Добавить жанр можно в файле data.sql");
    }

    @Override
    public Genre update(Genre genre) {
        throw new UnsupportedOperationException("Обновить жанр можно в файле data.sql");
    }

}
