package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
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

    @Override
    public User getId(long id) {
        return userStorage.getUserId(id);
    }

    // USER.Добавить в друзья
    public User addToFriends(long userId, long friendId) {
        User user = userStorage.getUserId(userId);
        User friend = userStorage.getUserId(friendId);
        user.getFriends().add(friendId);
        friend.getFriends().add(userId);
        return user;
    }

    // USER.Удалить из друзей
    public User removeFromFriends(long userId, long friendId) {
        User user = userStorage.getUserId(userId);
        User friend = userStorage.getUserId(friendId);
        user.getFriends().remove(friendId);
        friend.getFriends().remove(userId);
        return user;
    }

    // USER.Получить список друзей
    public List<User> getFriends(long userId) {
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
    public List<User> getCommonFriends(long userId, long friendId) {
        List<User> mutualFriends = getFriends(userId);
        mutualFriends.retainAll(getFriends(friendId));
        return new ArrayList<>(mutualFriends);
    }

}
