package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final Map<Integer, User> userStorage = new HashMap<>();

    @GetMapping
    public HashMap<Integer, User> getUsers() {

        // добавить логирование

        return new HashMap<>(userStorage);
    }

    @PostMapping
    public String addUser(@RequestBody User newUser) {

        // реализовать логику
        // добавить логирование

        return newUser + " успешно добавлен";
    }

    @PutMapping
    public String updateOrAddUser(@RequestBody User user) {

        // реализовать логику
        // добавить логирование

        return user + " обновлен или добавлен";
    }

}
