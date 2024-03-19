package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.IUserService;

import java.util.List;

@RestController
@RequestMapping(value = "/ru/yandex/practicum/filmorate/storage/dao/impl/users")
public class UserController {
    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    // USER.Получить id пользователя
    @GetMapping(value = "/{id}")
    public User getId(@Validated
                      @PathVariable long id) {
        return service.getById(id);
    }

    // USER.Получить список пользователей
    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    // USER.Вернуть список пользователей, являющихся его друзьями
    @GetMapping(value = "/{id}/friends")
    public List<User> getFriends(@Validated
                                 @PathVariable long id) {
        return service.getFriends(id);
    }

    // USER. Вернуть список друзей, общих с другим пользователем
    @GetMapping(value = "/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@Validated
                                       @PathVariable long id,
                                       @PathVariable long otherId) {
        return service.getCommonFriends(id, otherId);
    }

    // USER.Создать пользователя
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Validated
                       @RequestBody User user) {
        return service.create(user);
    }

    // USER.Создать или Обновить пользователя
    @PutMapping
    public User update(@Validated
                       @RequestBody User user) {
        return service.update(user);
    }

    // USER.Добавить в друзья
    @PutMapping(value = "/{id}/friends/{friendId}")
    public User addFriend(@PathVariable long id,
                          @PathVariable long friendId) {
        return service.addToFriends(id, friendId);
    }

    // USER.Удалить из друзей
    @DeleteMapping(value = "/{id}/friends/{friendId}")
    public User deleteFriend(@PathVariable long id,
                             @PathVariable long friendId) {
        return service.removeFromFriends(id, friendId);
    }

}


