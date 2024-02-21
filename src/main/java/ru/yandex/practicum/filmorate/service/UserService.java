package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;

@Service
public class UserService implements IService<User> {

    /*
    Создайте UserService, который будет отвечать за такие операции с пользователями,
    как добавление в друзья, удаление из друзей, вывод списка общих друзей.
    Пока пользователям не надо одобрять заявки в друзья — добавляем сразу.
    То есть если Лена стала другом Саши, то это значит, что Саша теперь друг Лены.
     */

    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }


    @Override
    public List<User> getAll() {
        return userStorage.getUsers();
    }

    @Override
    public User create(User newUser) {
        return userStorage.createUser(newUser);
    }

    @Override
    public User update(User user) {
        return userStorage.updateOrCreateUser(user);
    }

    // Добавить новые методы из ТЗ10

}
