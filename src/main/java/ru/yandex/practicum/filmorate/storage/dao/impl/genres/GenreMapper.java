package ru.yandex.practicum.filmorate.storage.dao.impl.genres;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.models.Friends;
import ru.yandex.practicum.filmorate.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getLong("id"));
        genre.setName(resultSet.getString("name"));
        return genre;
    }

}
