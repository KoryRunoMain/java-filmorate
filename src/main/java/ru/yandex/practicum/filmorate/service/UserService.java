package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;

@Service
public class UserService implements IService<User> {

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

    /*
       НОВЫЕ
       МЕТОДЫ
       ТЗ-10
    */
    // USER.Получить user по id
    @Override
    public User getId(Integer id) {
        return null;
    }

    // USER.Добавить в друзья
    public User addToFriends() {
        return null;
    }

    // USER.Удалить из друзей
    public User removeFromFriends() {
        return null;
    }
    // USER.Получить список друзей
    public List<User> getFriends() {
        return null;
    }
    // USER.Получить список общих друзей
    public List<User> getMutualFriends() {
        return null;
    }

}
