package ru.yandex.practicum.filmorate.service.verifyService;

import ru.yandex.practicum.filmorate.models.User;

public interface IVerifyUser {

    void verifyUserExist(long userId); // Проверить пользователя в БД

    void validateUserFields(User user); // Проверить поля на корректные переданные данные

    void verifyUserBeforeCreateFriendship(long userId, long friendId); // Проверить пользователей перед добавлением в друзья

    void verifyUserBeforeDeleteFriendship(long userId, long friendId); // Проверить пользователей перед удалением из друзей

    void verifyUserBeforeDelete(long userId); // Проверить пользователя перед удалением

    boolean verifyFriend(long userId, long friendId); // Проверить статус дружбы у пользователей

    boolean isUserNotExist(long userId); // Проверить есть ли пользователь с id в БД

}
