package ru.yandex.practicum.filmorate.storage.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.storage.dao.GenreDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Repository
@Primary
public class GenreDaoImpl implements GenreDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GenreDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // GENRE.Получить жанр по id из БД
    @Override
    public Genre getById(int id) {
        String selectQuery = "SELECT * FROM genres WHERE id=?";
        Genre genre = jdbcTemplate.queryForObject(selectQuery, new Object[]{id}, this::mapRow);
        log.info("Жанр с id {} получен", id);
        return genre;
    }

    // GENRE.Удалить жанр у фильма по id из БД
    @Override
    public void deleteById(long filmId) {
        String deleteQuery = "DELETE FROM film_genre WHERE film_id=?";
        jdbcTemplate.update(deleteQuery, filmId);
        log.info("Жанры фильма id {} удалены", filmId);
    }

    // GENRE.Получить список жанров из БД
    @Override
    public List<Genre> getAll() {
        String sql = "SELECT id, name FROM genres ORDER BY id";
        List<Genre> genres = jdbcTemplate.query(sql, this::mapRow);
        log.info("Список жанров получен");
        return genres;
    }

    // GENRE.Добавить жанр к фильму в БД
    @Override
    public void addGenreToFilm(long filmId, Set<Genre> genres) {
        String insertQuery = "INSERT INTO film_genre (film_id, genre_id) " +
                "VALUES(?, ?)";
        jdbcTemplate.batchUpdate(insertQuery,
                genres,
                genres.size(),
                (PreparedStatement ps, Genre g) -> {
                    ps.setLong(1, filmId);
                    ps.setLong(2, g.getId());
                });
    }

    // GENRE.Обновить жанры фильма в БД
    @Override
    public void updateFilmGenres(long filmId, Set<Genre> genres) {
        deleteById(filmId);
        addGenreToFilm(filmId, genres);
    }

    // GENRE.Получить жанры фильма из БД
    @Override
    public Set<Genre> getFilmGenres(long filmId) {
        String selectQuery = "SELECT g.id, g.name " +
                "FROM film_genre AS fg " +
                "LEFT JOIN genres AS g ON fg.genre_id = g.id " +
                "WHERE fg.film_id=? " +
                "ORDER BY g.id";
        Set<Genre> genres = new LinkedHashSet<>(
                jdbcTemplate.query(selectQuery, new Object[]{filmId}, this::mapRow));
        log.info("Получены все жанры фильма id {}", filmId);
        return genres;
    }

    // GENRE.Отображение данных полученных из БД на объект класса GENRE
    private Genre mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getLong("id"));
        genre.setName(resultSet.getString("name"));
        return genre;
    }

}
