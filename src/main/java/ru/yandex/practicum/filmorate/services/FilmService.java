package ru.yandex.practicum.filmorate.services;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.models.Film;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class FilmService {

    private final Map<Integer, Film> filmStorage = new HashMap<>();
    private static int COUNT = 0;

    public List<Film> getFilms() {
        return List.copyOf(filmStorage.values());
    }

    public void createFilm(Film newfilm) {
        filmExist(newfilm);
        validateFilm(newfilm);
        newfilm.setId(++COUNT);
        filmStorage.put(newfilm.getId(), newfilm);
    }

    public void updateFilm(Film film) {
        filmExist(film);
        validateFilm(film);
        filmStorage.put(film.getId(), film);
    }

    private void validateFilm(Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            log.info("Ошибка валидации даты релиза. Дата релиза — не раньше 28 декабря 1895 года");
            throw new ValidationException("Дата релиза — не раньше 28 декабря 1895 года");
        }
    }

    private void filmExist(Film film) {
        if (filmStorage.containsValue(film)) {
            log.info("Ошибка валидации film. Фильм уже добавлен");
            throw new ValidationException("Фильм уже добавлен");
        }
    }

}
