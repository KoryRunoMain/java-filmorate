package ru.yandex.practicum.filmorate.storage.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.storage.dao.FilmDao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@Primary
public class FilmDaoImpl implements FilmDao {
    private final JdbcTemplate jdbcTemplate;

    public FilmDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // FILM.Добавить фильм в БД
    @Override
    public Film create(Film film) {
        String sqlQuery = "INSERT INTO films (mpa_rating_id, name, description, release_date, duration) " +
                "VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlQuery,
                film.getMpa().getId(),
                film.getName(),
                film.getDescription(),
                Date.valueOf(film.getReleaseDate()),
                film.getDuration());
        String selectQuery = "SELECT id, mpa_rating_id, name, description, release_date, duration " +
                "FROM films " +
                "WHERE mpa_rating_id=? " +
                "AND name=? " +
                "AND description=? " +
                "AND release_date=? " +
                "AND duration=?";
        Film newFilm = jdbcTemplate.queryForObject(selectQuery, new Object[]{
                film.getMpa().getId(),
                film.getName(),
                film.getDescription(),
                Date.valueOf(film.getReleaseDate()),
                film.getDuration()}, this::mapRow);
        log.info("Фильм добавлен в БД id: {}", newFilm);
        return newFilm;
    }

    // FILM.Обновить фильм в БД
    @Override
    public Film update(Film film) {
        String updateQuery = "UPDATE films " +
                "SET mpa_rating_id=?, name=?, description=?, release_date=?, duration=? " +
                "WHERE id=?";
        jdbcTemplate.update(updateQuery,
                film.getMpa().getId(),
                film.getName(),
                film.getDescription(),
                Date.valueOf(film.getReleaseDate()),
                film.getDuration(),
                film.getId());
        Film updatedFilm = getById(film.getId());
        log.info("Фильм обновлен в БД id: {}", updatedFilm);
        return updatedFilm;
    }

    // FILM.Получить фильм по id из БД
    @Override
    public Film getById(long id) {
        String selectQuery = "SELECT id, mpa_rating_id, name, description, release_date, duration " +
                "FROM films " +
                "WHERE id = ?";
        Film film = jdbcTemplate.queryForObject(selectQuery, new Object[]{id}, this::mapRow);
        log.info("Получен фильм с id: {}", id);
        return film;
    }

    // FILM.Удалить фильм по id из БД
    @Override
    public void deleteById(long id) {
        String deleteQuery = "DELETE FROM films WHERE id=?";
        jdbcTemplate.queryForRowSet(deleteQuery, id);
        log.info("Фильм удален id: {}", id);
    }

    // FILM.Получить список фильмов из БД
    @Override
    public List<Film> getAll() {
        String selectQuery = "SELECT id, mpa_rating_id, name, description, release_date, duration " +
                "FROM films";
        List<Film> films = jdbcTemplate.query(selectQuery, this::mapRow);
        log.info("Получен список фильмов.");
        return films;
    }

    // FILM.Получить список популярных фильмов из БД
    @Override
    public List<Film> getPopularFilms(int count) {
        String selectQuery = "SELECT id, mpa_rating_id, name, description, release_date, duration " +
                "FROM films AS f " +
                "LEFT JOIN likes AS l ON f.id = l.film_id " +
                "GROUP BY f.id " +
                "ORDER BY COUNT(l.user_id) DESC " +
                "LIMIT ?";
        List<Film> film = jdbcTemplate.query(selectQuery, new Object[]{count}, this::mapRow);
        log.info("Получен список популярных фильмов.");
        return film;
    }

    // FILM.Отображение данных полученных из БД на объект класса FILM
    private Film mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Film film = new Film();
        MPARating mpaRating = new MPARating();
        mpaRating.setId(resultSet.getLong("mpa_rating_id"));

        film.setId(resultSet.getLong("id"));
        film.setMpa(mpaRating);
        film.setName(resultSet.getString("name"));
        film.setDescription(resultSet.getString("description"));
        film.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
        film.setDuration(resultSet.getInt("duration"));
        return film;
    }

}
