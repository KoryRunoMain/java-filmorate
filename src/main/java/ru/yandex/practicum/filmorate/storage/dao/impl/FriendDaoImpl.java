package ru.yandex.practicum.filmorate.storage.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.models.Friends;
import ru.yandex.practicum.filmorate.storage.dao.FriendDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@Primary
public class FriendDaoImpl implements FriendDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FriendDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createFriendship(long userId, long friendId, boolean isCommon) {
        String insertQuery = "INSERT INTO friends (friend_id, user_id, status) " +
                "VALUES(?, ?, ?)";
        jdbcTemplate.update(insertQuery, friendId, userId, isCommon);
        log.info("В друзьях оба пользователя ids {} , {}", userId, friendId);
    }

    @Override
    public void deleteFriendship(long userId, long friendId) {
        String deleteQuery = "DELETE FROM friends " +
                "WHERE user_id=? AND friend_id=?";
        String updateQuery = "UPDATE friends SET status=false " +
                "WHERE user_id=? AND friend_id=?";
        Friends friends = Objects.requireNonNull(getFriendsConnection(userId, friendId));
        jdbcTemplate.update(deleteQuery, userId, friendId);
        if (friends.getIsCommon()) {
            jdbcTemplate.update(updateQuery, userId, friendId);
        }
        log.info("Дружба между id {} , {} удалена.", userId, friendId);
    }

    @Override
    public List<Long> getAllFriendsRequests(long id) {
        String friendRequests = "SELECT friend_id, user_id, status " +
                "FROM friends " +
                "WHERE user_id=?";
        List<Friends> friendsList = jdbcTemplate.query(friendRequests, new Object[]{id}, this::mapRow);
        List<Long> requestIds = new ArrayList<>();
        for (Friends friend : friendsList) {
            requestIds.add(friend.getFriendId());
        }
        log.info("Получены запросы в друзья.");
        return requestIds;
    }

    @Override
    public Friends getFriendsConnection(long userId, long friendId) {
        String selectQuery = "SELECT friend_id, user_id, status " +
                "FROM friends " +
                "WHERE user_id=? AND friend_id=?";
        Friends friends = jdbcTemplate.queryForObject(selectQuery,
                new Object[]{userId, friendId}, this::mapRow);
        log.info("Связь между пользователями id {} , {}", userId, friendId);
        return friends;
    }

    private Friends mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        long userId = resultSet.getLong("user_id");
        long friendId = resultSet.getLong("friend_id");
        boolean status = resultSet.getBoolean("status");
        return new Friends(userId, friendId, status);
    }

}
