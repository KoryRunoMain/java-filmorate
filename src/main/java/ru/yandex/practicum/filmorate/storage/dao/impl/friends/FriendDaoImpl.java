package ru.yandex.practicum.filmorate.storage.dao.impl.friends;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.Friends;
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
    public List<Friends> getAllFriends(Long id) {
        return jdbcTemplate.query(
                "SELECT user_id, " +
                        "friend_id, " +
                        "status" +
                    "FROM friends",
                new FriendMapper()
        );
    }
}
