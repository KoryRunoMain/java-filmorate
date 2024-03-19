package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.models.Genre;
import ru.yandex.practicum.filmorate.service.IGenreService;

import java.util.List;

@RestController
@RequestMapping("/ru/yandex/practicum/filmorate/storage/dao/impl/genres")
public class GenreController {
    private final IGenreService service;

    @Autowired
    public GenreController(IGenreService service) {
        this.service = service;
    }

    @GetMapping
    public Genre getGenre(long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return service.getAll();
    }

}
