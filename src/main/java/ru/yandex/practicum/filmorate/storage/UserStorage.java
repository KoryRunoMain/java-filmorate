package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.User;

import java.util.List;

@Component
public interface UserStorage {

    List<User> getUsers();

    User getUserId(long id);

    User createUser(User newUser);

    User updateOrCreateUser(User user);

}
