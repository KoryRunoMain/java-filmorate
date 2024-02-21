package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import java.util.List;

@RestController
@RequestMapping(value = "/films")
public class FilmController extends AbstractController<Film> {

    @Autowired
    public FilmController(FilmService filmService) {
        super(filmService);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Film>> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Film> create(@Validated @RequestBody Film newFilm) {
        return super.create(newFilm);
    }

    @Override
    @PutMapping
    public ResponseEntity<Film> update(@Validated @RequestBody Film film) {
        return super.update(film);
    }

    // дописать эндпойнты по ТЗ-10

}
