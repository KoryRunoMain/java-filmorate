package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.services.FilmService;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping(value = "/films")
@Slf4j
public class FilmController {

    private final FilmService filmService;

    public FilmController() {
        filmService = new FilmService();
    }

    @GetMapping
    public ResponseEntity<List<Film>> getFilms() {
        log.info("Список фильмов получен");
        return new ResponseEntity<>(filmService.getFilms(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Film> createFilm(@Validated @RequestBody Film newFilm) {
        try {
            filmService.createFilm(newFilm);
            log.info("Фильм добавлен");
            return new ResponseEntity<>(newFilm, HttpStatus.CREATED);
        } catch (ValidationException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(newFilm, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Film> updateOrCreateFilm(@Validated @RequestBody Film film) {
        try {
            filmService.updateFilm(film);
            log.info("Фильм обновлен или добавлен");
            return new ResponseEntity<>(film, HttpStatus.OK);
        } catch (ValidationException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(film, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
