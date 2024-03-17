package ru.yandex.practicum.filmorate.storage.dao.impl.likes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.Like;
import ru.yandex.practicum.filmorate.storage.dao.LikeDao;

import java.util.List;

@Component
public class LikeDaoImpl implements LikeDao {
    private final JdbcTemplate jdbcTemplate;

    public LikeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void addLike(Long filmId, Long useId) {
    }

    @Override
    public void deleteLike(Long filmId, Long userId) {
    }

    @Override
    public List<Like> getLikes(Long filmId) {
        return jdbcTemplate.query(
                "SELECT film_id, " +
                        "user_id" +
                "FROM likes",
                new LikeMapper()
        );
    }

    @Override
    public List<Film> getPopularFilms(int count) {
        return null;
    }
}
