package ru.yandex.practicum.filmorate.storage.dao.impl.films;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.service.services.MPAService;
import ru.yandex.practicum.filmorate.storage.dao.FilmDao;
import ru.yandex.practicum.filmorate.storage.dao.GenreDao;
import ru.yandex.practicum.filmorate.storage.dao.LikeDao;
import ru.yandex.practicum.filmorate.storage.dao.MPADao;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Component
public class FilmDaoImpl implements FilmDao {
    private final JdbcTemplate jdbcTemplate;
    private final GenreDao genreDao;
    private final MPAService mpaService;
    private final LikeDao likeDao;

    public FilmDaoImpl(JdbcTemplate jdbcTemplate,
                       GenreDao genreDao,
                       MPAService mpaService,
                       LikeDao likeDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.genreDao = genreDao;
        this.mpaService = mpaService;
        this.likeDao = likeDao;
    }


    @Override
    public Film createFilm(Film newFilm) {
        return null;
    }

    @Override
    public Film updateFilm(Film film) {
        return null;
    }

    @Override
    public Film deleteFilm(Long id) {
        return null;
    }

    @Override
    public Film getFilmById(Long id) {
        return null;
    }

    @Override
    public List<Film> getAllFilms() throws SQLException {
        return jdbcTemplate.query(
                "SELECT id, " +
                        "mpa_rating_id, " +
                        "name, " +
                        "description, " +
                        "release_date, " +
                        "duration" +
                    "FROM films",
                new FilmMapper()
        );
    }
}
