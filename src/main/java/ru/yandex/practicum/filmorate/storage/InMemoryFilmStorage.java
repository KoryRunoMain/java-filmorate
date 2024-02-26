package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.models.Film;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Long, Film> filmStorage = new HashMap<>();
    private Long ID = 0L;


    @Override
    public List<Film> getFilms() {
        log.debug("Список фильмов получен");
        return new ArrayList<>(filmStorage.values());
    }

    @Override
    public Film getFilmId(Long id) {
        if (!filmStorage.containsKey(id)) {
            throw new FilmNotFoundException(id + " не найден");
        }
        return filmStorage.get(id);
    }

    @Override
    public Film createFilm(Film film) {
        filmExist(film);
        validateFilm(film);
        film.setId(++ID);
        filmStorage.put(film.getId(), film);
        log.debug("Фильм добавлен");
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        filmExist(film);
        validateFilm(film);
        filmStorage.put(film.getId(), film);
        log.debug("Фильм добавлен или обновлен");
        return film;
    }

    private void validateFilm(Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Ошибка валидации даты релиза. Дата релиза — не раньше 28 декабря 1895 года");
        }
    }

    private void filmExist(Film film) {
        if (filmStorage.containsValue(film)) {
            throw new ValidationException("Ошибка валидации film. Фильм уже добавлен");
        }
    }

}
