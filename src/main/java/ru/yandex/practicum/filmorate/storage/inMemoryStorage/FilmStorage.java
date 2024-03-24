package ru.yandex.practicum.filmorate.storage.inMemoryStorage;

import ru.yandex.practicum.filmorate.models.Film;

import java.util.List;

public interface FilmStorage {

    List<Film> getFilms();

    Film getFilmId(long id);

    Film createFilm(Film newFilm);

    Film updateFilm(Film film);

}
