package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Friends;

import java.util.List;
import java.util.Optional;

public interface FriendDao {

    void createFriendship(long userId, long friendId, boolean isCommon);

    Friends deleteFriendship(long fromId, long toId);

    List<Friends> getAllFriends(long id);

}
