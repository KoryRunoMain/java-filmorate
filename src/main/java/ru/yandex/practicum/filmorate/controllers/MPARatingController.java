package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.service.IMPAService;

import java.util.List;

@RestController
@RequestMapping("/mpa")
public class MPARatingController {
    private final IMPAService service;

    @Autowired
    public MPARatingController(IMPAService service) {
        this.service = service;
    }

    // MPARating.Получить рейтинг по id
    @GetMapping("/{id}")
    public MPARating getMpaRating(@PathVariable long id) {
        return service.getById(id);
    }

    // MPARating.Получить список всех рейтингов
    @GetMapping
    public List<MPARating> getAllMpaRatings() {
        return service.getAll();
    }

}
