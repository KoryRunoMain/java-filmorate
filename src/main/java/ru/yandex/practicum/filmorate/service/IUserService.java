package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.models.User;

import java.util.List;

public interface IUserService extends IService<User> {

    void addToFriends(long userId, long friendId); // Добавить в друзья

    void removeFromFriends(long userId, long friendId); // Удалить из друзей

    List<User> getFriendsList(long userId); // Получить список всех друзей пользователя

    List<User> getCommonFriends(long userId, long friendId); // Получить список друзей, общих с другим пользователем

    void deleteUser(long userId);

}
