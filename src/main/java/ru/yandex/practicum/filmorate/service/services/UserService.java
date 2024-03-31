package ru.yandex.practicum.filmorate.service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.IUserService;
import ru.yandex.practicum.filmorate.service.verifyService.IVerifyUser;
import ru.yandex.practicum.filmorate.storage.dao.FriendDao;
import ru.yandex.practicum.filmorate.storage.dao.UserDao;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements IUserService {
    private final UserDao userDao;
    private final FriendDao friendDao;
    private final IVerifyUser verify;

    @Autowired
    public UserService(UserDao userDao, FriendDao friendDao, IVerifyUser verify) {
        this.userDao = userDao;
        this.friendDao = friendDao;
        this.verify = verify;
    }

    // USER.Создать пользователя
    @Override
    public User create(User newUser) {
        // Проверка
        verify.validateUserFields(newUser);
        // Создание пользователя
        User user = userDao.create(newUser);
        log.info("Пользователь создан id: {}", user.getId());
        return user;
    }

    // USER.Обновить данные пользователя
    @Override
    public User update(User user) {
        // Проверка
        verify.validateUserFields(user);
        // Обновление данных
        User updatedUser = userDao.update(user);
        log.info("Данные пользователя обновлены id: {}", updatedUser.getId());
        return updatedUser;
    }

    // USER.Получить пользователя по id
    @Override
    public User getById(long id) {
        User user = userDao.getById(id);
        log.info("Получен пользователь id: {}", id);
        return user;
    }

    // USER.Удалить пользователя по id
    @Override
    public void deleteUser(long userId) {
        // Проверка
        verify.verifyUserBeforeDelete(userId);
        // Удаление
        userDao.deleteById(userId);
        log.info("пользователь удален id: {}", userId);
    }

    // USER.Получить всех пользователей
    @Override
    public List<User> getAll() {
        List<User> users = userDao.getAll();
        log.info("Получен список пользователей.");
        return users;
    }

    // FRIENDS.Добавить в друзья
    @Override
    public void addToFriends(long userId, long friendId) {
        // Проверка
        verify.verifyUserBeforeCreateFriendship(userId, friendId);
        // Добавление
        boolean isCommon = verify.verifyFriend(userId, friendId);
        friendDao.createFriendship(userId, friendId, isCommon);
        log.info("Пользователи добавлены в друзья userId: {}, friendId: {}", userId, friendId);
    }

    // FRIENDS.Удалить из друзей
    @Override
    public void removeFromFriends(long userId, long friendId) {
        // Проверка
        verify.verifyUserBeforeDeleteFriendship(userId, friendId);
        if (!verify.verifyFriend(userId, friendId)) {
            log.info("Попытка удаления из друзей userId: {} , friendId: {} ", userId, friendId
                    + " Пользователи не в друзьях.");
            return;
        }
        // Удаление
        friendDao.deleteFriendship(userId, friendId);
    }

    // FRIENDS.Получить список друзей
    @Override
    public List<User> getFriendsList(long userId) {
        // Проверка
        verify.verifyUserExist(userId);
        // Получение
        List<User> users = friendDao.getAllFriendsRequests(userId).stream()
                .map(userDao::getById)
                .collect(Collectors.toList());
        log.info("Получен список пользователей в друзьях userId: {}", userId);
        return users;
    }

    // FRIENDS.Получить список общих друзей
    @Override
    public List<User> getCommonFriends(long userId, long friendId) {
        List<Long> userIds = friendDao.getAllFriendsRequests(userId);
        List<Long> friendIds = friendDao.getAllFriendsRequests(friendId);
        List<User> users = userIds.stream()
                .filter(friendIds::contains)
                .map(userDao::getById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        log.info("Получен список общих друзей userId: {}", userId);
        return users;
    }

}
