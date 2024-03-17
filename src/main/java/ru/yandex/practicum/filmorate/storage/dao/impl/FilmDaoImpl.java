package ru.yandex.practicum.filmorate.storage.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.dao.FilmDao;
import ru.yandex.practicum.filmorate.storage.dao.GenreDao;
import ru.yandex.practicum.filmorate.storage.dao.LikeDao;
import ru.yandex.practicum.filmorate.storage.dao.MPADao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Optional<Film> get(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Film> delete(long id) {
        return Optional.empty();
    }

    @Override
    public boolean isElementContains(long id) {
        return false;
    }

    @Override
    public List<Film> getAll() {
        return jdbcTemplate.query(
                "SELECT id, " +
                        "mpa_rating_id, " +
                        "name, " +
                        "description, " +
                        "release_date, " +
                        "duration" +
                        "FROM films",
                this::mapRow
        );
    }

    @Override
    public void createGenre(long filmId, Set<Genre> genres) {

    }

    @Override
    public void updateGenre(long filmId, Set<Genre> genres) {

    }

    @Override
    public void getAllGenres(long id) {

    }

    private Film mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Film film = new Film();
        MPARating mpaRating = new MPARating();
        film.setId(
                resultSet.getLong("id"));
        mpaRating.setId(
                resultSet.getInt("mpa_rating_id"));
        film.setName(
                resultSet.getString("name"));
        film.setDescription(
                resultSet.getString("description"));
        film.setReleaseDate(
                resultSet.getDate("release_date").toLocalDate());
        film.setDuration(
                resultSet.getInt("duration"));
        return film;
    }


}
