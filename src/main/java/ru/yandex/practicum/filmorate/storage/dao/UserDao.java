package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.IStorageService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends IStorageService<User> {

    User createUser(User newUser);

    User updateUser(User user);

    Optional<User> findUserById(Long id);

    Optional<User> deleteUser(Long id);

    List<User> getAllUsers() throws SQLException;

}
