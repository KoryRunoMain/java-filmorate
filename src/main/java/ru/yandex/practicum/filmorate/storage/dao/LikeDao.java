package ru.yandex.practicum.filmorate.storage.dao;

public interface LikeDao {

    void create(long filmId, long userId); // Поставить лайк фильму

    void delete(long filmId, long userId); // Удалить лайк у фильма

    int getFilmLikes(long filmId); // Получить список лайков фильма

}
