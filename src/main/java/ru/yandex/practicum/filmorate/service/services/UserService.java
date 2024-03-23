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

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User create(User newUser) {
        validateUser(newUser);
        return userDao.create(newUser);
    }

    @Override
    public User update(User user) {
        validateUser(user);
        return userDao.update(user);
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public void addToFriends(long userId, long friendId) {
        checkFriendToCreate(userId, friendId);
        boolean isCommon = checkFriend(userId, friendId);
        friendDao.createFriendship(userId, friendId, isCommon);
    }

    @Override
    public void removeFromFriends(long userId, long friendId) {
        checkFriendToDelete(userId, friendId);
        friendDao.deleteFriendship(userId, friendId);
    }

    // USER.Получить список друзей
    @Override
    public List<User> getFriendsList(long userId) {
        if (!userExist(userId)) {
            throw new NotFoundException("Пользователь отсутствует");
        }
        List<User> friends = friendDao.getAllFriendsRequests(userId).stream()
                .mapToLong(Long::valueOf)
                .mapToObj(userDao::getById)
                .collect(Collectors.toList());
        return friends;
    }

    // USER.Получить список общих друзей
    @Override
    public List<User> getCommonFriends(long userId, long friendId) {
        List<Long> userIds = friendDao.getAllFriendsRequests(userId);
        List<Long> friendIds = friendDao.getAllFriendsRequests(friendId);
        List<User> commonFriends = new ArrayList<>();

        for (Long id : userIds) {
            if (friendIds.contains(id)) {
                User user = userDao.getById(id);
                if (user != null) {
                    commonFriends.add(user);
                }
            }
        }
        return commonFriends;
    }

    private void validateUser(User user) {
        if (user.getLogin().contains(" ")) {
            throw new ValidationException("Ошибка валидации логина. Логин не может содержать пробелы");
        }
        if (!user.getEmail().matches("^[a-zA-Z0-9_+&*-]+(?:" +
                "\\.[a-zA-Z0-9_+&*-]+)*" +
                "@(?:[a-zA-Z0-9-]+" +
                "\\.)+[a-zA-Z]{2,7}$")) {
            throw new ValidationException("Ошибка валидации e-mail. Пример: example@example.com");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            log.info("Поле имени использует адрес эл.почты: " + user.getEmail());
            user.setName(user.getLogin());
        }
    }

    private void checkFriendToCreate(long userId, long friendId) {
        if (userId == friendId) {
            throw new ValidationException("Пользователь не может удалить сам себя из друзей.");
        }
        if (!userExist(userId)) {
            throw new NotFoundException("Пользователь не найден");
        }
        if (!userExist(friendId)) {
            throw new NotFoundException("Пользователь не найден");
        }
        if (checkFriend(userId, friendId)) {
            throw new NotFoundException("Пользователи уже в друзьях");
        }
    }

    private void checkFriendToDelete(long userId, long friendId) {
        if (userId == friendId) {
            throw new ValidationException("Пользователь не может удалить сам себя из друзей.");
        }
        if (!userExist(userId)) {
            throw new NotFoundException("Пользователь не найден");
        }
        if (!userExist(friendId)) {
            throw new NotFoundException("Пользователь не найден");
        }
        if (!checkFriend(userId, friendId)) {
            throw new NotFoundException("Пользователи не в друзьях");
        }
    }

    private boolean checkFriend(long userId, long friendId) {
        try {
            friendDao.getFriendsConnection(userId, friendId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    private boolean userExist(long userId) {
        try {
            userDao.getById(userId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
