package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.User;

import java.util.List;

@Component
public interface UserStorage {

    List<User> getUsers();

    User createUser(User object);

    User updateOrCreateUser(User user);

    List<User> getFriends();
}
