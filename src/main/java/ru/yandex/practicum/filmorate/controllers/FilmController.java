package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.Film;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping(value = "/films")
@Slf4j
public class FilmController {

    private final Map<Integer, Film> filmStorage = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<Film>> getFilms() {
        log.info("Список всех фильмов получен");
        return new ResponseEntity<>(List.copyOf(filmStorage.values()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Film> createFilm(@Valid @RequestBody Film newFilm) {
        validateFilm(newFilm);
        newFilm.setId(filmStorage.size() + 1);
        filmStorage.put(newFilm.getId(), newFilm);
        log.info("Фильм добавлен");
        return new ResponseEntity<>(newFilm, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Film> updateOrCreateFilm(@Valid @RequestBody Film film) {
        validateFilm(film);
        filmStorage.put(film.getId(), film);
        log.info("Фильм обновлен или добавлен");
        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    private void validateFilm(Film film) {
        if (film.getName() == null || film.getName().isEmpty()) {
            // добавить ЛОГ
            throw new ValidationException("Название не может быть пустым");
        }
        if (film.getDescription() != null && film.getDescription().length() > 200) {
            // добавить ЛОГ
            throw new ValidationException("Максимальная длина описания — 200 символов");
        }
        if (film.getReleaseDate() != null && film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            // добавить ЛОГ
            throw new ValidationException("Ошибка. Дата релиза — не раньше 28 декабря 1895 года;");
        }
        if (film.getDuration() <= 0) {
            // добавить ЛОГ
            throw new ValidationException("Продолжительность фильма должна быть положительной");
        }
    }

}
