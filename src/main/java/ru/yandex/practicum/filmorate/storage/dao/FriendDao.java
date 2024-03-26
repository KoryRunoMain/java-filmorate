package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Friends;

import java.util.List;

public interface FriendDao {

    void createFriendship(long userId, long friendId, boolean isCommon); // Добавить в друзья пользователя

    void deleteFriendship(long userId, long friendId); // Удалить из друзей пользователя

    Friends getFriendsConnection(long userId, long friendId); // Получить статус дружбы пользователей

    List<Long> getAllFriendsRequests(long id); // Получить список заявок в друзья

}
