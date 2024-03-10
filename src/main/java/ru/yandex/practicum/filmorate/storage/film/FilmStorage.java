package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.Film;

import java.util.List;

@Component
public interface FilmStorage {

    List<Film> getFilms();

    Film getFilmId(long id);

    Film createFilm(Film newFilm);

    Film updateFilm(Film film);

}
