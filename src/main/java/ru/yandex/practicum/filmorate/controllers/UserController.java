package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.User;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private static final Map<Integer, User> userStorage = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        log.info("Список всех польтзователей получен");
        return new ResponseEntity<>(List.copyOf(userStorage.values()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User newUser) {
        if (!userStorage.containsKey(newUser.getId()) && isValidateUser(newUser)) {
            newUser.setId(userStorage.size() + 1);
            userStorage.put(newUser.getId(), newUser);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(newUser, HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<User> updateOrCreateUser(@Valid @RequestBody User user) {
        if (isUserExist(user)) {
            log.info("Попытка авторизации уже существующего пользователя");
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
        isValidateUser(user);
        userStorage.put(user.getId(), user);
        log.info("Пользователь добавлен или обновлен");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private boolean isValidateUser(User user) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            log.info("Электронная почта не может быть пустой. пример: example@example.com");
            return false;
        }
        if (user.getLogin() == null || user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
            log.info("Логин не может быть пустым и содержать пробелы");
            return false;
        }
        if (user.getBirthday() != null && user.getBirthday().isAfter(LocalDate.now())) {
            log.info("Дата рождения не может быть в будущем");
            return false;
        }
        return true;
    }

    private boolean isUserExist(User user) {
        for (User userEmail : userStorage.values()) {
            if (Objects.equals(userEmail.getEmail(), user.getEmail())) {
                return true;
            }
        }
        return false;
    }

}
