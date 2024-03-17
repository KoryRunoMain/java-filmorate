package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    User createUser(User newUser);

    User getUserById(Long id);

    User updateUser(User user);

    User deleteUser(Long id);

    List<User> getAllUsers() throws SQLException;

}
