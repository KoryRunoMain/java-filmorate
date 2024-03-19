package ru.yandex.practicum.filmorate.storage.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.storage.dao.FilmDao;
import ru.yandex.practicum.filmorate.storage.dao.GenreDao;
import ru.yandex.practicum.filmorate.storage.dao.LikeDao;
import ru.yandex.practicum.filmorate.storage.dao.MPADao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class FilmDaoImpl implements FilmDao {
    private final JdbcTemplate jdbcTemplate;
    private final GenreDao genreDao;
    private final MPADao mpaDao;
    private final LikeDao likeDao;

    public FilmDaoImpl(JdbcTemplate jdbcTemplate,
                       GenreDao genreDao,
                       MPADao mpaDao,
                       LikeDao likeDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.genreDao = genreDao;
        this.mpaDao = mpaDao;
        this.likeDao = likeDao;
    }

    @Override
    public Film create(Film object) {
        return null;
    }

    @Override
    public Film update(Film film) {
        return null;
    }

    @Override
    public Film getById(long id) {
        return null;
    }

    @Override
    public Film deleteById(long id) {
        return null;
    }

    @Override
    public List<Film> getAll() {
        return jdbcTemplate.query(
                "SELECT id, mpa_rating_id, name, description, release_date, duration" +
                    "FROM films",
                this::mapRow
        );
    }

    @Override
    public void addGenreToFilm(Film film) {
    }

    @Override
    public void updateFilmGenres(Film film) {
    }

    @Override
    public List<Genre> getFilmGenres(long id) {
        return null;
    }

    private Film mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Film film = new Film();
        MPARating mpaRating = new MPARating();
        film.setId(resultSet.getLong("id"));
        mpaRating.setId(resultSet.getLong("mpa_rating_id"));
        film.setName(resultSet.getString("name"));
        film.setDescription(resultSet.getString("description"));
        film.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
        film.setDuration(resultSet.getInt("duration"));
        return film;
    }


}
