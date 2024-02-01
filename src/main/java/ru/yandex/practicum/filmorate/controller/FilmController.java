package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.Map;
import java.util.TreeMap;


@RestController
@RequestMapping(value = "/films")
public class FilmController {

    private final Map<Integer, Film> filmStorage = new TreeMap<>();

    @GetMapping
    public Map<Integer, Film> getFilms() {

        // добавить логирование

        return new TreeMap<>(filmStorage);
    }

    @PostMapping
    public String addFilm(@Valid @RequestBody Film neFilm) {

        // реализовать логику
        // добавить логирование

        return neFilm + " успешно добавлен";
    }

    @PutMapping
    public String updateOrAddFilm(@Valid @RequestBody Film film) {

        // Реализовать логику
        // добавить логирование

        return film + " обновлен или добавлен";
    }

}
