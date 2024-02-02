package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.Film;

import javax.validation.Valid;
import java.util.Map;
import java.util.TreeMap;


@RestController
@RequestMapping(value = "/films")
@Slf4j
public class FilmController {

    private final Map<Integer, Film> filmStorage = new TreeMap<>();

    @GetMapping
    public Map<Integer, Film> getFilms() {
        log.info("Список всех фильмов получен");
        return new TreeMap<>(filmStorage);
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film newFilm) {
        filmStorage.put(newFilm.getId(), newFilm);
        log.info("Фильм добавлен");
        return newFilm;
    }

    @PutMapping
    public Film updateOrCreateFilm(@Valid @RequestBody Film film) {
        filmStorage.put(film.getId(), film);
        log.info("Фильм обновлен или добавлен");
        return film;
    }

}
