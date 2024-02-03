package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import ru.yandex.practicum.filmorate.models.User;

import javax.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final Map<Integer, User> userStorage = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        log.info("Список всех польтзователей получен");
        return new ResponseEntity<>(List.copyOf(userStorage.values()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User newUser) {
        if (!userStorage.containsKey(newUser.getId())) {
            newUser.setId(userStorage.size() + 1);
            userStorage.put(newUser.getId(), newUser);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(newUser, HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<User> updateOrCreateUser(@Valid @RequestBody User user) {
        userStorage.put(user.getId(), user);
        log.info("Пользователь добавлен или обновлен");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
