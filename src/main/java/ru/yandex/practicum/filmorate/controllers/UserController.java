package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.List;


@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // USER.Получить id пользователя
    @GetMapping(value = "/{id}")
    public User getId(@Validated
                      @PathVariable long id) {
        return userService.getId(id);
    }

    // USER.Получить список пользователей
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    // USER.Вернуть список пользователей, являющихся его друзьями
    @GetMapping(value = "/{id}/friends")
    public List<User> getFriends(@Validated
                                 @PathVariable long id) {
        return userService.getFriends(id);
    }

    // USER. Вернуть список друзей, общих с другим пользователем
    @GetMapping(value = "/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@Validated
                                       @PathVariable long id,
                                       @PathVariable long otherId) {
        return userService.getCommonFriends(id, otherId);
    }

    // USER.Удалить из друзей
    @DeleteMapping(value = "/{id}/friends/{friendId}")
    public User deleteFriend(@PathVariable long id,
                             @PathVariable long friendId) {
        return userService.removeFromFriends(id, friendId);
    }

    // USER.Создать пользователя
    @PostMapping
    public User create(@Validated
                       @RequestBody User user) {
        return userService.create(user);
    }

    // USER.Создать или Обновить пользователя
    @PutMapping
    public User update(@Validated
                       @RequestBody User user) {
        return userService.update(user);
    }

    // USER.Добавить в друзья
    @PutMapping(value = "/{id}/friends/{friendId}")
    public User addFriend(@PathVariable long id,
                          @PathVariable long friendId) {
        return userService.addToFriends(id, friendId);
    }

}


