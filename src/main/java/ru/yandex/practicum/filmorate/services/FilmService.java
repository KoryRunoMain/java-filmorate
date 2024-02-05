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


    public List<Film> getFilms() {
        return List.copyOf(filmStorage.values());
    }

    public Film createFilm(Film newfilm) {
        filmExist(newfilm);
        validateFilm(newfilm);
        newfilm.setId(filmStorage.size() + 1);
        filmStorage.put(newfilm.getId(), newfilm);
        return newfilm;
    }

    public Film updateFilm(Film film) {
        filmExist(film);
        validateFilm(film);
        filmStorage.put(film.getId(), film);
        return film;
    }

    private void validateFilm(Film film) {
        if (film.getName() == null || film.getName().isEmpty()) {
            log.info("Ошибка валидации имени. Название не может быть пустым");
            throw new ValidationException("Название не может быть пустым");
        }
        if (film.getDescription() != null && film.getDescription().length() > 200) {
            log.info("Ошибка валидации описания. Максимальная длина описания — 200 символов");
            throw new ValidationException("Максимальная длина описания — 200 символов");
        }
        if (film.getReleaseDate() != null && film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            log.info("Ошибка валидации даты релиза. Дата релиза — не раньше 28 декабря 1895 года");
            throw new ValidationException("Дата релиза — не раньше 28 декабря 1895 года");
        }
        if (film.getDuration() <= 0) {
            log.info("Ошибка валидации продолдительности фильма. Продолжительность должна быть положительной");
            throw new ValidationException("Продолжительность должна быть положительной");
        }
    }

    private void filmExist(Film film) {
        for (Film f : filmStorage.values()) {
            if (f.getName().equals(film.getName())
                    && f.getDescription().equals(film.getDescription())
                    && f.getReleaseDate().equals(film.getReleaseDate())
                    && f.getDuration() == film.getDuration()) {
                log.info("Ошибка валидации film. Фильм уже добавлен");
                throw new ValidationException("Фильм уже добавлен");
            }
        }
    }

}
