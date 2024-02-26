package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import java.util.*;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {

    private final Map<Long, User> userStorage = new HashMap<>();
    private Long ID = 0L;


    @Override
    public List<User> getUsers() {
        log.debug("Список всех пользователей получен");
        return new ArrayList<>(userStorage.values());
    }

    @Override
    public User getUserId(Long id) {
        if (!userStorage.containsKey(id)) {
            throw new UserNotFoundException(id + " не найден");
        }
        return userStorage.get(id);
    }

    @Override
    public User createUser(User newUser) {
        userExist(newUser);
        validateUser(newUser);
        newUser.setId(++ID);
        userStorage.put(newUser.getId(), newUser);
        log.debug("Пользовтель добавлен");
        return newUser;
    }

    @Override
    public User updateOrCreateUser(User user) {
        userExist(user);
        validateUser(user);
        userStorage.put(user.getId(), user);
        log.debug("Пользовтель добавлен или обновлен");
        return user;
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
            log.debug("Поле имени использует адрес эл.почты");
            user.setName(user.getLogin());
        }
    }

    private void userExist(User user) {
        for (User userEmail : userStorage.values()) {
            if (Objects.equals(userEmail.getEmail(), user.getEmail())) {
                throw new ValidationException("Ошибка валидации user. Пользователь с таким email уже существует");
            }
        }
    }

}
