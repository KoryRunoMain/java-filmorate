package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.Friends;
import ru.yandex.practicum.filmorate.storage.dao.FriendDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class FriendDaoImpl implements FriendDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FriendDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void createFriendship(long userId, long friendId, boolean isCommon) {
    }

    @Override
    public Friends deleteFriendship(long fromId, long toId) {
        return null;
    }

    @Override
    public List<Friends> getAllFriends(long id) {
        return jdbcTemplate.query(
                "SELECT user_id, " +
                        "friend_id, " +
                        "status" +
                        "FROM friends",
                this::mapRow
        );
    }

    private Friends mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Friends friends = new Friends();
        friends.setUserId(resultSet.getLong("user_id"));
        friends.setFriendId(resultSet.getLong("friend_id"));
        friends.setIsCommon(resultSet.getBoolean("status"));
        return friends;
    }

}
