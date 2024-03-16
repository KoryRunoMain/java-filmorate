package ru.yandex.practicum.filmorate.storage.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.dao.FriendDao;

import java.util.List;

@Component
public class FriendDaoImpl implements FriendDao {
    private final JdbcTemplate jdbcTemplate;

    public FriendDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void addFriend(Long userId, Long friendId) {
    }

    @Override
    public void deleteFriend(Long userId, Long friendId) {
    }

    @Override
    public List<User> getAllFriends(Long id) {
        return null;
    }
}
