package ru.yandex.practicum.filmorate.storage.dao.impl.friends;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.models.Friends;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendMapper implements RowMapper<Friends> {

    @Override
    public Friends mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Friends friends = new Friends();
        friends.setUserId(resultSet.getLong("user_id"));
        friends.setFriendId(resultSet.getLong("friend_id"));
        friends.setIsCommon(resultSet.getBoolean("status"));
        return friends;
    }

}
