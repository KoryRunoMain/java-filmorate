package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.Like;
import ru.yandex.practicum.filmorate.storage.dao.LikeDao;
import ru.yandex.practicum.filmorate.storage.dao.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class LikeDaoImpl implements LikeDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final LikeDao likeDao;

    @Autowired
    public LikeDaoImpl(JdbcTemplate jdbcTemplate,
                       UserDao userDao,
                       LikeDao likeDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
        this.likeDao = likeDao;
    }


    @Override
    public void create(long filmId, long userId) {
    }

    @Override
    public Optional<Like> delete(long filmId, Long userId) {
        return Optional.empty();
    }

    @Override
    public List<Like> getLikes(long filmId) {
        return jdbcTemplate.query(
                "SELECT film_id, " +
                        "user_id" +
                        "FROM likes",
                this::mapRow
        );
    }

    @Override
    public boolean isElementContains(long filmId, long userId) {
        return false;
    }

    private Like mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Like userLike = new Like();
        userLike.setFilmId(
                resultSet.getLong("film_id"));
        userLike.setUserId(
                resultSet.getLong("user_id"));
        return userLike;
    }

}
