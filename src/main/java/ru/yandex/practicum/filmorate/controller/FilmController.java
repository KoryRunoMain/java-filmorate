package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

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
    public String addFilm(@Valid @RequestBody Film newFilm) {
        // реализовать логику
        // добавить логирование

        log.info("Добавление фильма в список");
        return newFilm + " успешно добавлен";
    }

    @PutMapping
    public String updateOrAddFilm(@Valid @RequestBody Film film) {

        // Реализовать логику
        // добавить логирование

        return film + " обновлен или добавлен";
    }

}
