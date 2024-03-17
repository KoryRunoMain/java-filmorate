package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.DbService;

import java.util.Optional;

public interface UserDao extends DbService<User> {

    Optional<User> delete(long id);

}
