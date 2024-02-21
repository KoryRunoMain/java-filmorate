package ru.yandex.practicum.filmorate.controllers;

import org.springframework.http.ResponseEntity;
import ru.yandex.practicum.filmorate.models.IModel;

import java.util.List;

public interface IController<T> {

    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> create(T obj);

    ResponseEntity<T> update(T obj);

}