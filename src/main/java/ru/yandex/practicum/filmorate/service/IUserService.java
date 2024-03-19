package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.models.User;

import java.util.List;

public interface IUserService extends IService<User> {

    User addToFriends(long userId, long friendId);

    User removeFromFriends(long userId, long friendId);

    List<User> getFriends(long userId);

    List<User> getCommonFriends(long userId, long friendId);

}
