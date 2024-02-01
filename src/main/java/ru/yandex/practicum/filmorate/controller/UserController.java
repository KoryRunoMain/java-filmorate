package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final Map<Integer, User> userStorage = new HashMap<>();

    // добавить логирование

    @GetMapping
    public HashMap<Integer, User> getUsers() {
        return new HashMap<>(userStorage);
    }

    @PutMapping
    public String addUser(@RequestBody User newUser) throws ValidationException {

        // написать логику

        return newUser + " успешно добавлен.";
    }

    @PostMapping
    public String updateOrAddUser(@RequestBody User user) throws ValidationException {

        // написать логику

        return user + " обновлен или добавлен.";
    }

}
