package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.IUserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    // USER.Получить id пользователя
    @GetMapping(value = "/{id}")
    public User getId(@Validated @PathVariable long id) {
        return service.getById(id);
    }

    // USER.Получить список пользователей
    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    // FRIENDS.Вернуть список пользователей, являющихся его друзьями
    @GetMapping(value = "/{id}/friends")
    public List<User> getFriends(@Validated @PathVariable long id) {
        return service.getFriendsList(id);
    }

    // FRIENDS. Вернуть список друзей, общих с другим пользователем
    @GetMapping(value = "/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@Validated @PathVariable long id, @PathVariable long otherId) {
        return service.getCommonFriends(id, otherId);
    }

    // USER.Создать пользователя
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Validated @RequestBody User user) {
        return service.create(user);
    }

    // USER.Создать или Обновить пользователя
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User update(@Validated @RequestBody User user) {
        return service.update(user);
    }

    // FRIENDS.Добавить в друзья
    @PutMapping(value = "/{userId}/friends/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    public void addFriend(@PathVariable long userId, @PathVariable long friendId) {
        service.addToFriends(userId, friendId);
    }

    // FRIENDS.Удалить из друзей
    @DeleteMapping(value = "/{userId}/friends/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFriend(@PathVariable long userId, @PathVariable long friendId) {
        service.removeFromFriends(userId, friendId);
    }

    // USER.Удалить пользователя
    @DeleteMapping(value = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long userId) {
        service.deleteUser(userId);
    }

}


