package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.inMemoryStorage.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

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
        user.addFriend(friendId);
        friend.addFriend(userId);
        return user;
    }

    // USER.Удалить из друзей
    public User removeFromFriends(long userId, long friendId) {
        User user = userStorage.getUserId(userId);
        User friend = userStorage.getUserId(friendId);
        user.deleteFriend(friendId);
        friend.deleteFriend(userId);
        return user;
    }

    // USER.Получить список друзей
    public List<User> getFriends(long userId) {
        User user = userStorage.getUserId(userId);
        if (user.getFriends() == null) {
            throw new NotFoundException(userId + " не найден");
        }
        return user.getFriends().stream()
                .map(userStorage::getUserId)
                .collect(Collectors.toList());
    }

    // USER.Получить список общих друзей
    public List<User> getCommonFriends(long userId, long friendId) {
        return getFriends(userId).stream()
                .filter(getFriends(friendId)::contains)
                .collect(Collectors.toList());
    }

}
