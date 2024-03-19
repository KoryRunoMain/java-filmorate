package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.service.IMpaService;

import java.util.List;

@RestController
@RequestMapping("/mpa_ratings")
public class MPARatingController {
    private final IMpaService service;

    @Autowired
    public MPARatingController(IMpaService service) {
        this.service = service;
    }

    @GetMapping
    public MPARating getMpaRating(long id) {
        return (MPARating) service.getById(id);
    }

    @GetMapping
    public List<MPARating> getAllMpaRatings() {
        return service.getAll();
    }

}
