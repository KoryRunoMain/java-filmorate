package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.User;

import java.util.List;

public interface UserDao {

    User create(User user); // Создать пользователя

    User update(User user); // Обновить данные пользователя

    User getById(long userId); // Получить пользователя по идентификатору

    void deleteById(long userId); // Удалить пользователя по идентификатору

    List<User> getAll(); // Получить список пользователей

}
