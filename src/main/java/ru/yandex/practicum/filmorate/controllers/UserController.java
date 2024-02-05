package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.services.UserService;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController() {
        userService = new UserService();
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        log.info("Список всех пользователей получен");
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User newUser) {
        try {
            userService.createUser(newUser);
            log.info("Пользователь добавлен");
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (ValidationException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(newUser, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<User> updateOrCreateUser(@Valid @RequestBody User user) {
        try {
            userService.updateOrCreateUser(user);
            log.info("Пользователь добавлен или обновлен");
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ValidationException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
    }

}
