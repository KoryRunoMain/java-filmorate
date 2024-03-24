package ru.yandex.practicum.filmorate.storage.inMemoryStorage;

import ru.yandex.practicum.filmorate.models.User;

import java.util.List;

public interface UserStorage {

    List<User> getUsers();

    User getUserId(long id);

    User createUser(User newUser);

    User updateOrCreateUser(User user);

}
