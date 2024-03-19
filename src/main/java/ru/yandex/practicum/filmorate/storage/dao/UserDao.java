package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    User create(User user);

    User update(User user);

    User getById(long id);

    User deleteById(long id);

    List<User> getAll();

}
