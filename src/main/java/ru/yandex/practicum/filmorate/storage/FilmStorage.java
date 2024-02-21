package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.service.IService;

import java.util.List;

@Component
public interface FilmStorage {

    List<Film> getFilms();

    Film createFilm(Film newFilm);

    Film updateFilm(Film film);

    List<Film> getPopularFilms(int count);

}
