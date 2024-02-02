package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeptions.ValidationException;
import ru.yandex.practicum.filmorate.models.User;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final Map<String, User> userStorage = new HashMap<>();
    private int generatorId = 0;

    @GetMapping
    public Collection<User> getUsers() {
        log.info("Список users получен");
        return userStorage.values();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User newUser) {
        if (userStorage.containsKey(newUser.getEmail())) {
            throw new ValidationException("Пользователь с электронной почтой " +
                    newUser.getEmail() + " уже зарегистрирован.");
        }
        newUser.setId(getNextId());
        userStorage.put(getNameIfEmpty(newUser.getName(), newUser.getLogin()), newUser);
        log.info("Пользователь добавлен");
        return newUser;
    }

    @PutMapping
    public User updateOrCreateUser(@Valid @RequestBody User user) {
        userStorage.put(user.getEmail(), user);
        log.info("Пользователь добавлен или обновлен");
        return user;
    }

    private int getNextId() {
        return ++generatorId;
    }

    // имя для отображения может быть пустым — в таком случае будет использован логин
    private String getNameIfEmpty(String name, String login) {
        if (name.isEmpty() || name.isBlank()) {
            log.info("Поле имени использует адрес эл.почты");
            return login;
        }
        return name;
    }



}
