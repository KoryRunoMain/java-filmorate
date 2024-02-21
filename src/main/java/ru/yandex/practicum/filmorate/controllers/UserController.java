package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.UserService;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController extends AbstractController<User> {

    @Autowired
    public UserController(UserService userService) {
        super(userService);
    }


    @Override
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@Validated @RequestBody User newUser) {
        return super.create(newUser);
    }

    @Override
    @PutMapping
    public ResponseEntity<User> update(@Validated @RequestBody User user) {
        return super.update(user);
    }

    // дописать эндпойнты по ТЗ-10

}


