package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.Film;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Integer, Film> filmStorage = new HashMap<>();
    private static int ID = 0;

    @Override
    public List<Film> getFilms() {
        log.debug("Список фильмов получен");
        return List.copyOf(filmStorage.values());
    }

    @Override
    public Film createFilm(Film newfilm) {
        filmExist(newfilm);
        validateFilm(newfilm);
        newfilm.setId(++ID);
        filmStorage.put(newfilm.getId(), newfilm);
        log.debug("Фильм добавлен");
        return newfilm;
    }

    @Override
    public Film updateFilm(Film film) {
        filmExist(film);
        validateFilm(film);
        filmStorage.put(film.getId(), film);
        log.debug("Фильм добавлен или обновлен");
        return film;
    }

    // FILM.Получить список популярных фильмов по лайкам
    @Override
    public List<Film> getPopularFilms(int count) {
        return null;
    }

    private void validateFilm(Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            log.debug("Ошибка валидации даты релиза. Дата релиза — не раньше 28 декабря 1895 года");
            throw new ValidationException("Дата релиза — не раньше 28 декабря 1895 года");
        }
    }

    private void filmExist(Film film) {
        if (filmStorage.containsValue(film)) {
            log.debug("Ошибка валидации film. Фильм уже добавлен");
            throw new ValidationException("Фильм уже добавлен");
        }
    }

}
