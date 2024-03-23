package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Friends;

import java.util.List;

public interface FriendDao {

    void createFriendship(long userId, long friendId, boolean isCommon);

    void deleteFriendship(long userId, long friendId);

    Friends getFriendsConnection(long userId, long friendId);

    List<Long> getAllFriendsRequests(long id);

}
