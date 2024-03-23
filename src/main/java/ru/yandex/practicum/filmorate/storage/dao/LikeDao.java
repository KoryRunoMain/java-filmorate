package ru.yandex.practicum.filmorate.storage.dao;

public interface LikeDao {

    void create(long filmId, long userId);

    void delete(long filmId, long userId);

    int getFilmLikes(long filmId);

}
