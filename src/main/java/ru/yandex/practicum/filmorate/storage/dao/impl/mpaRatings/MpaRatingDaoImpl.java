package ru.yandex.practicum.filmorate.storage.dao.impl.mpaRatings;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.storage.dao.MPADao;

import java.util.List;

@Component
public class MpaRatingDaoImpl implements MPADao {
    private final JdbcTemplate jdbcTemplate;

    public MpaRatingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public MPARating getMpaById(Long id) {
        return null;
    }

    @Override
    public List<MPARating> getAllMpaRatings() {
        return jdbcTemplate.query(
                "SELECT id, " +
                        "name",
                new MpaRatingMapper()
        );
    }
}
