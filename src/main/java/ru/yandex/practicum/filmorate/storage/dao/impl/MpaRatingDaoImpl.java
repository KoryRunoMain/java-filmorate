package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.storage.dao.MPADao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class MpaRatingDaoImpl implements MPADao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MpaRatingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isContains(long id) {
        return false;
    }

    @Override
    public Optional<MPARating> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<MPARating> getAll() {
        return null;
    }

    private MPARating mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        MPARating mpaRating = new MPARating();
        mpaRating.setId(
                resultSet.getInt("is"));
        mpaRating.setName(
                resultSet.getString("name"));
        return mpaRating;
    }

}
