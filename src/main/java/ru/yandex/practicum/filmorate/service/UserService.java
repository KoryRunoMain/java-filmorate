package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import java.util.*;

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

    @Override
    public User getId(Long id) {
        return userStorage.getUserId(id);
    }

    /*
       НОВЫЕ
       МЕТОДЫ
       ТЗ-10
    */
    // USER.Добавить в друзья
    public User addToFriends(Long userId, Long friendId) {
        User user = userStorage.getUserId(userId);
        if (user == null) {
            throw new UserNotFoundException(userId + " не найден");
        }
        User friend = userStorage.getUserId(friendId);
        if (friend == null) {
            throw new UserNotFoundException(friendId + " не найден");
        }
        user.getFriends().add(friendId);
        friend.getFriends().add(userId);
        return user;
    }

    // USER.Удалить из друзей
    public User removeFromFriends(Long userId, Long friendId) {
        User user = userStorage.getUserId(userId);
        if (user == null) {
            throw new UserNotFoundException(userId + " не найден");
        }
        User friend = userStorage.getUserId(friendId);
        if (friend == null) {
            throw new UserNotFoundException(friendId + " не найден");
        }
        user.getFriends().remove(friendId);
        friend.getFriends().remove(userId);
        return user;
    }
    // USER.Получить список друзей
    public List<User> getFriends(Long userId) {
        User user = userStorage.getUserId(userId);
        if (user.getFriends() == null) {
            return new ArrayList<>();
        }
        List<User> friends = new ArrayList<>();
        for (Long uId : user.getFriends()) {
            friends.add(userStorage.getUserId(uId));
        }
        return friends;
    }
    // USER.Получить список общих друзей
    public List<User> getCommonFriends(Long userId, Long friendId) {
        List<User> mutualFriends = getFriends(userId);
        mutualFriends.retainAll(getFriends(friendId));
        return new ArrayList<>(mutualFriends);
    }

}
