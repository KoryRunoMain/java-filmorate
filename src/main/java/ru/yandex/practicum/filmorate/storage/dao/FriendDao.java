package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.User;

import java.util.List;

public interface FriendDao {

    void addFriend(Long userId, Long friendId);

    void deleteFriend(Long userId, Long friendId);

    List<User> getAllFriends(Long id);

}
