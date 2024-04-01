package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.service.IFilmService;

import java.util.List;

@RestController
@RequestMapping(value = "films")
public class FilmController {
    private final IFilmService service;

    @Autowired
    public FilmController(IFilmService service) {
        this.service = service;
    }

    // FILM.Получить список фильмов
    @GetMapping
    public List<Film> getAll() {
        return service.getAll();
    }

    // FILM.Получить id фильма
    @GetMapping(value = "/{id}")
    public Film getId(@PathVariable long id) {
        return service.getById(id);
    }

    /*
    FILM.Вернуть список из первых count фильмов по количеству лайков.
    Если значение параметра count не задано, верните первые 10.
    */
    @GetMapping(value = "/popular")
    public List<Film> getPopularFilms(@RequestParam(name = "count", defaultValue = "10") int count) {
        return service.getPopularFilms(count);
    }

    // FILM.Создать фильм
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film create(@Validated @RequestBody Film film) {
        return service.create(film);
    }

    // FILM.Обновить фильм
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Film update(@Validated @RequestBody Film film) {
        return service.update(film);
    }

    // LIKE.Поставить лайк фильму
    @PutMapping(value = "/{filmId}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void addLike(@PathVariable long filmId, @PathVariable long userId) {
        service.likeFilm(filmId, userId);
    }

    // LIKE.Удалить лайк
    @DeleteMapping(value = "/{filmId}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLike(@PathVariable long filmId, @PathVariable long userId) {
        service.removeLike(filmId, userId);
    }

    // FILM.Удалить фильм
    @DeleteMapping(value = "/{filmId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long filmId) {
        service.deleteFilm(filmId);
    }

}
