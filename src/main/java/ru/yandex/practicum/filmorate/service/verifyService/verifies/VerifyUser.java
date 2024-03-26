package ru.yandex.practicum.filmorate.service.verifyService.verifies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.verifyService.IVerifyUser;
import ru.yandex.practicum.filmorate.storage.dao.FriendDao;
import ru.yandex.practicum.filmorate.storage.dao.UserDao;

@Slf4j
@Service
public class VerifyUser implements IVerifyUser {
    private final UserDao userDao;
    private final FriendDao friendDao;

    @Autowired
    public VerifyUser(UserDao userDao, FriendDao friendDao) {
        this.userDao = userDao;
        this.friendDao = friendDao;
    }

    // USER.Проверить пользователя
    public void verifyUserExist(long userId) {
        if (isUserExist(userId)) {
            throw new NotFoundException("Пользователь отсутствует");
        }
    }

    // USER.Проверить поля на корректные переданные данные
    public void validateUserFields(User user) {
        if (user.getLogin().contains(" ")) {
            log.info("Ошибка валидации логина userId: {}", user.getId());
            throw new ValidationException("Ошибка валидации логина. Логин не может содержать пробелы");
        }
        if (!user.getEmail().matches("^[a-zA-Z0-9_+&*-]+(?:" +
                "\\.[a-zA-Z0-9_+&*-]+)*" +
                "@(?:[a-zA-Z0-9-]+" +
                "\\.)+[a-zA-Z]{2,7}$")) {
            log.info("Ошибка валидации e-mail userId: {}", user.getId());
            throw new ValidationException("Ошибка валидации e-mail. Пример: example@example.example");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            log.info("Поле имени использует адрес эл.почты: {}", user.getEmail());
            user.setName(user.getLogin());
        }
    }

    // USER.Проверить пользователей перед добавлением в друзья
    public void verifyUserBeforeCreate(long userId, long friendId) {
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
    public void verifyUserBeforeDeleteFriendship(long userId, long friendId) {
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

    // USER.Проверить пользователя перед удалением
    public void verifyUserBeforeDelete(long userId) {
        if (isUserExist(userId)) {
            log.info("Пользователь не найден userId: {}", userId);
            throw new NotFoundException("Пользователь не найден");
        }
    }

    // USER.Проверить статус дружбы у пользователей
    public boolean verifyFriend(long userId, long friendId) {
        try {
            friendDao.getFriendsConnection(userId, friendId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    // USER.Проверить есть ли пользователь с id в БД
    public boolean isUserExist(long userId) {
        try {
            userDao.getById(userId);
            return false;
        } catch (EmptyResultDataAccessException e) {
            return true;
        }
    }

}
