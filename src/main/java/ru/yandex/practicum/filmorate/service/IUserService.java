package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.models.User;

import java.util.List;

public interface IUserService extends IService<User> {

    void addToFriends(long userId, long friendId);

    void removeFromFriends(long userId, long friendId);

    List<User> getFriendsList(long userId);

    List<User> getCommonFriends(long userId, long friendId);

}
