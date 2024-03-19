package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.Like;

import java.util.List;
import java.util.Optional;

public interface LikeDao {

    void create(long filmId, long userId);

    Like delete(long filmId, long userId);

    List<Like> getLikes(long filmId);

}
