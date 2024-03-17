package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.IStorageService;

import java.util.Optional;

public interface UserDao extends IStorageService<User> {

    Optional<User> delete(long id);

}
