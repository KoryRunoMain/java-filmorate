package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.models.User;

import java.util.*;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Long, User> userStorage = new HashMap<>();
    private long idCount = 0L;

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(userStorage.values());
    }

    @Override
    public User getUserId(long id) {
        if (!userStorage.containsKey(id)) {
            throw new NotFoundException(id + " не найден");
        }
        return userStorage.get(id);
    }

    @Override
    public User createUser(User newUser) {
        userExist(newUser);
        validateUser(newUser);
        newUser.setId(++idCount);
        userStorage.put(newUser.getId(), newUser);
        return newUser;
    }

    @Override
    public User updateOrCreateUser(User user) {
        userExist(user);
        validateUser(user);
        userStorage.put(user.getId(), user);
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
            log.info("Поле имени использует адрес эл.почты: " + user.getEmail());
            user.setName(user.getLogin());
        }
    }

    private void userExist(User user) {
        if (userStorage.values().stream()
                .anyMatch(u -> Objects.equals(u.getEmail(), user.getEmail()))) {
            throw new NotFoundException("Ошибка валидации user. Пользователь с таким email уже существует");
        }
    }

}