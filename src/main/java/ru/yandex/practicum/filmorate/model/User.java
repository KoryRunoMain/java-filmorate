package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * User.
 */
@Data
@NoArgsConstructor
public class User {

    // добавить библиотеку валидации и добавить аннотации для полей

    private Integer id;
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;
}
