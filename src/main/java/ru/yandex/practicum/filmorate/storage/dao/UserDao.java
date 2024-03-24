package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.User;

import java.util.List;

public interface UserDao {

    User create(User user); // Создать пользователя

    User update(User user); // Обновить данные пользователя

    User getById(long id); // Получить пользователя по идентификатору

    User deleteById(long id); // Удалить пользователя по идентификатору

    List<User> getAll(); // Получить список пользователей

}
