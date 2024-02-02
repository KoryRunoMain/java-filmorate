package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.User;

import javax.validation.Valid;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final Map<String, User> userStorage = new TreeMap<>();

    @GetMapping
    public TreeMap<String, User> getUsers() {
        // добавить логирование
        log.info("Список users получен");
        return new TreeMap<>(userStorage);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User newUser) {
        userStorage.put(newUser.getEmail(), newUser);
        log.info("Пользователь добавлен");
        return newUser;
    }

    @PutMapping
    public User updateOrCreateUser(@Valid @RequestBody User user) {
        userStorage.put(user.getEmail(), user);
        log.info("Пользователь добавлен или обновлен");
        return user;
    }

}
