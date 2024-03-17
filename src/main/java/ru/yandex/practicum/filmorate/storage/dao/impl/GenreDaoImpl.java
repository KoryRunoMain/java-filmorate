package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.storage.dao.GenreDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class GenreDaoImpl implements GenreDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GenreDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Genre create(Genre genre) {
        return genre;
    }

    @Override
    public Optional<Genre> get(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Genre> delete(long id) {
        return Optional.empty();
    }


    @Override
    public List<Genre> getAll() {
        return jdbcTemplate.query(
                "SELECT id, " +
                        "name",
                this::mapRow
        );
    }

    @Override
    public boolean isElementContains(long id) {
        return false;
    }

    private Genre mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Genre genre = new Genre();
        genre.setId(
                resultSet.getLong("id"));
        genre.setName(
                resultSet.getString("name"));
        return genre;
    }

}
