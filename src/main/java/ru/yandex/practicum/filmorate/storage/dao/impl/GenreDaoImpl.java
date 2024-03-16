package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.storage.dao.GenreDao;

import java.util.List;
import java.util.Set;

@Component
public class GenreDaoImpl implements GenreDao {
    private final JdbcTemplate jdbcTemplate;

    public GenreDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void addGenreToFilm(Film film) {
    }

    @Override
    public void deleteGenreFromFilm(Film film) {
    }

    @Override
    public Genre getGenreById(Long id) {
        return null;
    }

    @Override
    public Set<Genre> getFilmGenres(Long id) {
        return null;
    }

    @Override
    public List<Genre> getGenres() {
        return null;
    }
}
