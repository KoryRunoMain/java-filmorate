package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeptions.ValidationException;
import ru.yandex.practicum.filmorate.models.Film;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/films")
@Slf4j
public class FilmController {

    private final Map<Integer, Film> filmStorage = new HashMap<>();
    private int generatorId = 0;

    @GetMapping
    public Collection<Film> getFilms() {
        log.info("Список всех фильмов получен");
        return filmStorage.values();
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film newFilm) {
        for (Film film : filmStorage.values()) {
            if (film.getName().equals(newFilm.getName())
                    && film.getReleaseDate().equals(newFilm.getReleaseDate())) {
                throw new ValidationException("Фильм уже был добавлен");
            }
        }
        newFilm.setId(getNextId());
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

    private int getNextId() {
        return ++generatorId;
    }

}
