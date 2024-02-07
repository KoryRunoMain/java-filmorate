package ru.yandex.practicum.filmorate.services;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.models.User;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class UserService {

    private final Map<Integer, User> userStorage = new HashMap<>();
    private static int COUNT = 0;

    public List<User> getUsers() {
        return List.copyOf(userStorage.values());
    }

    public void createUser(User newUser) {
        userExist(newUser);
        validateUser(newUser);
        newUser.setId(++COUNT);
        userStorage.put(newUser.getId(), newUser);
    }

    public void updateOrCreateUser(User user) {
        userExist(user);
        validateUser(user);
        userStorage.put(user.getId(), user);
    }

    private void validateUser(User user) {
        if (user.getLogin().contains(" ")) {
            log.info("Ошибка валидации логина. Логин не может содержать пробелы");
            throw new ValidationException("Логин не может содержать пробелы");
        }
        if (!user.getEmail().matches("^[a-zA-Z0-9_+&*-]+(?:" +
                "\\.[a-zA-Z0-9_+&*-]+)*" +
                "@(?:[a-zA-Z0-9-]+" +
                "\\.)+[a-zA-Z]{2,7}$")) {
            log.info("Ошибка валидации e-mail");
            throw new ValidationException("Ошибка валидации e-mail. Пример: example@example.com");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            log.info("Поле имени использует адрес эл.почты");
            user.setName(user.getLogin());
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
