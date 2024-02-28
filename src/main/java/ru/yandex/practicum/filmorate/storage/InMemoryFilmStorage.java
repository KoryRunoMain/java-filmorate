package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.models.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Long, Film> filmStorage = new HashMap<>();
    private long idCount = 0L;

    @Override
    public List<Film> getFilms() {
        return new ArrayList<>(filmStorage.values());
    }

    @Override
    public Film getFilmId(long id) {
        if (!filmStorage.containsKey(id)) {
            throw new NotFoundException(id + " не найден");
        }
        return filmStorage.get(id);
    }

    @Override
    public Film createFilm(Film film) {
        filmExist(film);
        validateFilm(film);
        film.setId(++idCount);
        filmStorage.put(film.getId(), film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        filmExist(film);
        validateFilm(film);
        filmStorage.put(film.getId(), film);
        return film;
    }

    private void validateFilm(Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Ошибка валидации даты релиза. Дата релиза — не раньше 28 декабря 1895 года");
        }
    }

    private void filmExist(Film film) {
        if (filmStorage.containsValue(film)) {
            throw new NotFoundException("Ошибка валидации film. Фильм уже добавлен");
        }
    }

}
