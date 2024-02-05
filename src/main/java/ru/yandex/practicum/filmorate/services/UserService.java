package ru.yandex.practicum.filmorate.services;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.models.User;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class UserService {

    private final Map<Integer, User> userStorage = new HashMap<>();

    public List<User> getUsers() {
        return List.copyOf(userStorage.values());
    }

    public User createUser(User newUser) {
        userExist(newUser);
        validateUser(newUser);
        newUser.setId(userStorage.size() + 1);
        userStorage.put(newUser.getId(), newUser);
        return newUser;
    }

    public User updateOrCreateUser(User user) {
        userExist(user);
        validateUser(user);
        userStorage.put(user.getId(), user);
        return user;
    }

    private void validateUser(User user) {
        if (user.getEmail() == null || !user.getEmail().matches("^[a-zA-Z0-9_+&*-]+(?:" +
                "\\.[a-zA-Z0-9_+&*-]+)*" +
                "@(?:[a-zA-Z0-9-]+" +
                "\\.)+[a-zA-Z]{2,7}$")) {
            log.info("Ошибка валидации e-mail. Электронная почта не может быть пустой. пример: example@example.com");
            throw new ValidationException("Электронная почта не может быть пустой. пример: example@example.com");
        }
        if (user.getLogin() == null || user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
            log.info("Ошибка валидации логина. Логин не может быть пустым и содержать пробелы");
            throw new ValidationException("Логин не может быть пустым и содержать пробелы");
        }
        if (user.getBirthday() != null && user.getBirthday().isAfter(LocalDate.now())) {
            log.info("Ошибка валидации дня рождения. Дата рождения не может быть в будущем");
            throw new ValidationException("Дата рождения не может быть в будущем");
        }
    }

    private void userExist(User user) {
        for (User userEmail : userStorage.values()) {
            if (Objects.equals(userEmail.getEmail(), user.getEmail())) {
                log.info("Ошибка валидации user. Пользователь с таким email уже существует");
                throw new ValidationException("Пользователь с таким email уже существует");
            }
        }
    }

}
