package ru.yandex.practicum.filmorate.service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.IUserService;
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

    @Autowired
    public UserService(UserDao userDao, FriendDao friendDao) {
        this.userDao = userDao;
        this.friendDao = friendDao;
    }

    // USER.Создать пользователя
    @Override
    public User create(User newUser) {
        validateUserFields(newUser);
        return userDao.create(newUser);
    }

    // USER.Обновить данные пользователя
    @Override
    public User update(User user) {
        validateUserFields(user);
        return userDao.update(user);
    }

    // USER.Получить пользователя по id
    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    // USER.Получить всех пользователей
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    // FRIENDS.Добавить в друзья
    @Override
    public void addToFriends(long userId, long friendId) {
        verifyUserBeforeCreate(userId, friendId);
        boolean isCommon = verifyFriend(userId, friendId);
        friendDao.createFriendship(userId, friendId, isCommon);
    }

    // FRIENDS.Удалить из друзей
    @Override
    public void removeFromFriends(long userId, long friendId) {
        verifyUserBeforeDelete(userId, friendId);
        friendDao.deleteFriendship(userId, friendId);
    }

    // FRIENDS.Получить список друзей
    @Override
    public List<User> getFriendsList(long userId) {
        if (isUserExist(userId)) {
            throw new NotFoundException("Пользователь отсутствует");
        }
        return friendDao.getAllFriendsRequests(userId).stream()
                .map(userDao::getById)
                .collect(Collectors.toList());
    }

    // FRIENDS.Получить список общих друзей
    @Override
    public List<User> getCommonFriends(long userId, long friendId) {
        List<Long> userIds = friendDao.getAllFriendsRequests(userId);
        List<Long> friendIds = friendDao.getAllFriendsRequests(friendId);
        return userIds.stream()
                .filter(friendIds::contains)
                .map(userDao::getById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // USER.Проверить поля на корректные переданные данные
    private void validateUserFields(User user) {
        if (user.getLogin().contains(" ")) {
            log.info("Ошибка валидации логина userId: {}", user.getId());
            throw new ValidationException("Ошибка валидации логина. Логин не может содержать пробелы");
        }
        if (!user.getEmail().matches("^[a-zA-Z0-9_+&*-]+(?:" +
                "\\.[a-zA-Z0-9_+&*-]+)*" +
                "@(?:[a-zA-Z0-9-]+" +
                "\\.)+[a-zA-Z]{2,7}$")) {
            log.info("Ошибка валидации e-mail userId: {}", user.getId());
            throw new ValidationException("Ошибка валидации e-mail. Пример: example@example.com");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            log.info("Поле имени использует адрес эл.почты: {}", user.getEmail());
            user.setName(user.getLogin());
        }
    }

    // USER.Проверить пользователей перед добавлением в друзья
    private void verifyUserBeforeCreate(long userId, long friendId) {
        if (userId == friendId) {
            log.info("Попытка удаления самого себя userId: {}", userId);
            throw new ValidationException("Пользователь не может удалить сам себя из друзей.");
        }
        if (isUserExist(userId)) {
            log.info("Пользователь не найден userId: {}", userId);
            throw new NotFoundException("Пользователь не найден");
        }
        if (isUserExist(friendId)) {
            log.info("Пользователь не найден userId: {}", friendId);
            throw new NotFoundException("Пользователь не найден");
        }
        if (verifyFriend(userId, friendId)) {
            log.info("Попытка добавления в друзья повторно userId: {} , friendId: {} ", userId, friendId);
            throw new NotFoundException("Пользователи уже в друзьях");
        }
    }

    // USER.Проверить пользователей перед удалением из друзей
    private void verifyUserBeforeDelete(long userId, long friendId) {
        if (userId == friendId) {
            log.info("Попытка удаления самого себя userId: {}", userId);
            throw new ValidationException("Пользователь не может удалить сам себя из друзей.");
        }
        if (isUserExist(userId)) {
            log.info("Пользователь не найден userId: {}", userId);
            throw new NotFoundException("Пользователь не найден");
        }
        if (isUserExist(friendId)) {
            log.info("Пользователь не найден userId: {}", friendId);
            throw new NotFoundException("Пользователь не найден");
        }
        if (!verifyFriend(userId, friendId)) {
            log.info("Попытка удаления из друзей userId: {} , friendId: {} ", userId, friendId);
            throw new NotFoundException("Пользователи не в друзьях");
        }
    }

    // USER.Проверить статус дружбы у пользователей
    private boolean verifyFriend(long userId, long friendId) {
        try {
            friendDao.getFriendsConnection(userId, friendId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    // USER.Проверить есть ли пользователь с id в БД
    private boolean isUserExist(long userId) {
        try {
            userDao.getById(userId);
            return false;
        } catch (EmptyResultDataAccessException e) {
            return true;
        }
    }

}
