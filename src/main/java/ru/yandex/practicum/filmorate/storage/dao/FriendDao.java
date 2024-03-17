package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Friends;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.IStorageService;

import java.util.List;

public interface FriendDao extends IStorageService<User> {

    void addFriend(Long userId, Long friendId);

    void deleteFriend(Long userId, Long friendId);

    List<Friends> getAllFriends(Long id);

}
