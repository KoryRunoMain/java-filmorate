package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Friends;

import java.util.List;
import java.util.Optional;

public interface FriendDao {

    void create(long userId, long friendId, boolean isCommon);

    Optional<Friends> delete(long fromId, long toId);

    List<Friends> getAllFriends(long id);

    boolean isElementContains(long fromId, long toId);

}
