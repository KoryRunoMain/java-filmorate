package ru.yandex.practicum.filmorate.storage.dao;


import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.models.Like;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.IStorageService;

import java.util.List;

public interface LikeDao extends IStorageService<User> {

    void addLike(Long filmId, Long useId);

    void deleteLike(Long filmId, Long userId);

    List<Like> getLikes(Long filmId);

    List<Film> getPopularFilms(int count);

}
