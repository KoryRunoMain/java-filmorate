package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.models.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;

@RestController
@RequestMapping(value = "/films")
public class FilmController implements IController<Film> {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    // FILM.Получить список фильмов
    @Override
    @GetMapping
    public List<Film> getAll() {
        return filmService.getAll();
    }

    // FILM.Получить id фильма
    @Override
    @GetMapping(value = "/{id}")
    public Film getId(@PathVariable Long id) {
        return filmService.getId(id);
    }

    // FILM.Создать фильм
    @Override
    @PostMapping
    public Film create(@Validated @RequestBody Film film) {
        return filmService.create(film);
    }

    // FILM.Создать или Обновить фильм
    @Override
    @PutMapping
    public Film update(@Validated @RequestBody Film film) {
        return filmService.update(film);
    }

    // FILM.Поставить лайк фильму
    @PutMapping(value = "/{id}/like/{userId}")
    public Film addLike(@PathVariable Long id, @PathVariable Long userId) {
        return filmService.addLike(id, userId);
    }

    // FILM.Удалить лайк фальма
    @DeleteMapping(value = "/{id}/like/{userId}")
    public Film deleteLike(@PathVariable Long id, @PathVariable Long userId) {
        return filmService.removeLike(id, userId);
    }

    /* FILM.Вернуть список из первых count фильмов по количеству лайков.
       Если значение параметра count не задано, верните первые 10.
    */
    @GetMapping(value = "/popular")
    public List<Film> getPopularFilms(@RequestParam(name = "count", defaultValue = "10") Integer count) {
        return filmService.getPopularFilms(count);
    }

}
