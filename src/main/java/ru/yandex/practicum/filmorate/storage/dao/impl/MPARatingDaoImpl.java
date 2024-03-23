package ru.yandex.practicum.filmorate.storage.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.storage.dao.MPADao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@Primary
public class MPARatingDaoImpl implements MPADao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MPARatingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MPARating getById(int id) {
        String selectQuery = "SELECT * FROM mpa_ratings WHERE id=?";
        MPARating mpaRating = jdbcTemplate.queryForObject(selectQuery, new Object[]{id}, this::mapRow);
        log.info("Рейтинг с id {} получен.", id);
        return mpaRating;
    }

    @Override
    public List<MPARating> getAll() {
        String selectQuery = "SELECT id, name FROM mpa_ratings ORDER BY id";
        List<MPARating> mpaRatings = jdbcTemplate.query(selectQuery, this::mapRow);
        log.info("Список всех возрастных рейтингов получен.");
        return mpaRatings;
    }

    private MPARating mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        MPARating mpaRating = new MPARating();
        mpaRating.setId(resultSet.getLong("id"));
        mpaRating.setName(resultSet.getString("name"));
        return mpaRating;
    }

}
