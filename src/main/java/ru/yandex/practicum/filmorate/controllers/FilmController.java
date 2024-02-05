package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (isValidateFilm(newFilm)) {
            newFilm.setId(filmStorage.size() + 1);
            filmStorage.put(newFilm.getId(), newFilm);
            log.info("Фильм добавлен");
            return new ResponseEntity<>(newFilm, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(newFilm, HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Film> updateOrCreateFilm(@Valid @RequestBody Film film) {
        if (isFilmExist(film)) {
            log.info("Попытка плагиата. Фильм уже добавлен");
            return new ResponseEntity<>(film, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        isValidateFilm(film);
        filmStorage.put(film.getId(), film);
        log.info("Фильм обновлен или добавлен");
        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    private boolean isValidateFilm(Film film) {
        if (film.getName() == null || film.getName().isEmpty()) {
            log.info("Ошбка ввода имени. Название не может быть пустым");
            return false;
        }
        if (film.getDescription() != null && film.getDescription().length() > 200) {
            log.info("Ошбка ввода описания. Максимальная длина описания — 200 символов");
            return false;
        }
        if (film.getReleaseDate() != null && film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            log.info("Ошбка ввода даты релиза. Дата релиза — не раньше 28 декабря 1895 года");
            return false;
        }
        if (film.getDuration() <= 0) {
            log.info("Ошбка ввода продолдительности фильмаю Продолжительность фильма должна быть положительной");
            return false;
        }
        return true;
    }

    private boolean isFilmExist(Film film) {
        for (Film f : filmStorage.values()) {
            if (f.getName().equals(film.getName())
                    && f.getDescription().equals(film.getDescription())
                    && f.getReleaseDate().equals(film.getReleaseDate())
                    && f.getDuration() == film.getDuration()) {
                return true;
            }
        }
        return false;
    }

}
