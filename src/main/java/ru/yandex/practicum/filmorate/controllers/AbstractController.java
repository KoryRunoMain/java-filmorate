package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.yandex.practicum.filmorate.models.IModel;
import ru.yandex.practicum.filmorate.service.IService;

import java.util.List;

public abstract class AbstractController <T extends IModel> implements IController<T> {

    protected final IService<T> service;

    @Autowired
    protected AbstractController(IService<T> service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<T> create(T object) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(object));
    }

    @Override
    public ResponseEntity<T> update(T object) {
        T modifyObj = service.update(object);
        if (service.getAll().contains(object.getId())) {
            return ResponseEntity.ok(modifyObj);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(modifyObj);
    }

}
