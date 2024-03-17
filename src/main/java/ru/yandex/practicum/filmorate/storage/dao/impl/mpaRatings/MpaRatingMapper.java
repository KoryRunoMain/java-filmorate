package ru.yandex.practicum.filmorate.storage.dao.impl.mpaRatings;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.models.MPARating;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MpaRatingMapper implements RowMapper<MPARating> {

    @Override
    public MPARating mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        MPARating mpaRating = new MPARating();
        mpaRating.setId(resultSet.getInt("is"));
        mpaRating.setName(resultSet.getString("name"));
        return mpaRating;
    }

}
