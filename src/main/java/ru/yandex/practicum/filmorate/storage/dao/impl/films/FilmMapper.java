package ru.yandex.practicum.filmorate.storage.dao.impl.films;
import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.MPARating;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmMapper implements RowMapper<Film> {

    @Override
    public Film mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Film film = new Film();
        MPARating mpaRating = new MPARating();

        mpaRating.setId(resultSet.getInt("mpa_rating_id"));
        film.setId(resultSet.getLong("id"));
        film.setName(resultSet.getString("name"));
        film.setDescription(resultSet.getString("description"));
        film.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
        film.setDuration(resultSet.getInt("duration"));
        return film;
    }
}
