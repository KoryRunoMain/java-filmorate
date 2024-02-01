package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Film.
 */
@Data
@NoArgsConstructor
public class Film {

    // добавить библиотеку валидации и добавить аннотации для полей

    private Integer id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private long duration;
}
