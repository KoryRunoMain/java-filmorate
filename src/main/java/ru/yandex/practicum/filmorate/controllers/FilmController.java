package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.validations.ValidateFilm;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping(value = "/films")
@Slf4j
public class FilmController {

    private final ValidateFilm validate = new ValidateFilm();
    private final Map<Integer, Film> filmStorage = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<Film>> getFilms() {
        log.info("Список всех фильмов получен");
        return new ResponseEntity<>(List.copyOf(filmStorage.values()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Film> createFilm(@Valid @RequestBody Film newFilm) {
        validate.validateFilm(newFilm);
        newFilm.setId(filmStorage.size() + 1);
        filmStorage.put(newFilm.getId(), newFilm);
        log.info("Фильм добавлен");
        return new ResponseEntity<>(newFilm, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Film> updateOrCreateFilm(@Valid @RequestBody Film film) {
        validate.validateFilm(film);
        filmStorage.put(film.getId(), film);
        log.info("Фильм обновлен или добавлен");
        return new ResponseEntity<>(film, HttpStatus.OK);
    }

}
