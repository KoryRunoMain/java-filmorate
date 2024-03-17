package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.service.services.FilmService;

import java.util.List;

@RestController
@RequestMapping(value = "/ru/yandex/practicum/filmorate/storage/dao/impl/films")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    // FILM.Получить список фильмов
    @GetMapping
    public List<Film> getAll() {
        return filmService.getAll();
    }

    // FILM.Получить id фильма
    @GetMapping(value = "/{id}")
    public Film getId(@PathVariable long id) {
        return filmService.getId(id);
    }

    /*
    FILM.Вернуть список из первых count фильмов по количеству лайков.
    Если значение параметра count не задано, верните первые 10.
    */
    @GetMapping(value = "/popular")
    public List<Film> getPopularFilms(@RequestParam(name = "count", defaultValue = "10") int count) {
        return filmService.getPopularFilms(count);
    }

    // FILM.Создать фильм
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film create(@Validated
                       @RequestBody Film film) {
        return filmService.create(film);
    }

    // FILM.Создать или Обновить фильм
    @PutMapping
    public Film update(@Validated
                       @RequestBody Film film) {
        return filmService.update(film);
    }

    // FILM.Поставить лайк фильму
    @PutMapping(value = "/{id}/like/{userId}")
    public Film addLike(@PathVariable long id,
                        @PathVariable long userId) {
        return filmService.likeFilm(id, userId);
    }

    // FILM.Удалить лайк
    @DeleteMapping(value = "/{id}/like/{userId}")
    public Film deleteLike(@PathVariable long id,
                           @PathVariable long userId) {
        return filmService.removeLike(id, userId);
    }

}
