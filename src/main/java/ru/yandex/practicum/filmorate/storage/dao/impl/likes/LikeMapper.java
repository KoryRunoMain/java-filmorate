package ru.yandex.practicum.filmorate.storage.dao.impl.likes;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.models.Like;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LikeMapper implements RowMapper<Like> {

    @Override
    public Like mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Like userLike = new Like();
        userLike.setFilmId(resultSet.getLong("film_id"));
        userLike.setUserId(resultSet.getLong("user_id"));
        return userLike;
    }

}
