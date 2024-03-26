package ru.yandex.practicum.filmorate.storage.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.storage.dao.LikeDao;

@Slf4j
@Repository
@Primary
public class LikeDaoImpl implements LikeDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LikeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // LIKE.Поставить лайк фильму в БД
    @Override
    public void create(long filmId, long userId) {
        String insertQuery = "INSERT INTO likes (film_id, user_id) " +
                "VALUES(?, ?)";
        jdbcTemplate.update(insertQuery, filmId, userId);
        log.info("Добавлен лайк фильму id {} от ползьвателя id {}", filmId, userId);
    }

    // LIKE.Удалить лайк у фильма из БД
    @Override
    public void delete(long filmId, long userId) {
        String deleteQuery = "DELETE FROM likes " +
                "WHERE film_id=? AND user_id=?";
        jdbcTemplate.update(deleteQuery, filmId, userId);
        log.info("Удалён лайк фильма id {} от пользователя id {}", filmId, userId);
    }

    // LIKE.Получить кол-во лайков фильма из БД
    @Override
    public int getFilmLikes(long filmId) {
        String selectQuery = "SELECT COUNT(*) FROM likes " +
                "WHERE film_id=?";
        int filmLikes = jdbcTemplate.queryForObject(selectQuery, new Object[]{filmId}, Integer.class);
        log.info("Все лайки фильма id {} получены.", filmId);
        return filmLikes;
    }

}
