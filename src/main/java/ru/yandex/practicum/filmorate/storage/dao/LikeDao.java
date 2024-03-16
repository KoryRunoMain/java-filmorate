package ru.yandex.practicum.filmorate.storage.dao;


import ru.yandex.practicum.filmorate.models.Film;

import java.util.List;

public interface LikeDao {

    void addLike(Long filmId, Long useId);

    void deleteLike(Long filmId, Long userId);

    List<Long> getLikes(Long filmId);

    List<Film> getPopularFilms(int count);

}
