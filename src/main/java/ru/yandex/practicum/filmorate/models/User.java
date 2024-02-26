package ru.yandex.practicum.filmorate.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * User
 */
@Data
@AllArgsConstructor
public class User implements Model {

    private Long id;

    @NotNull(message = "электронная почта не может быть null")
    @NotBlank(message = "электронная почта не может быть пустой")
    private String email;

    @NotNull(message = "логин не может быть null")
    @NotBlank(message = "логин не может быть пустым")
    private String login;

    private String name;

    @NotNull(message = "дата рождения не может быть null")
    @Past(message = "дата рождения не может быть в будущем")
    private LocalDate birthday;

    private final Set<Long> friends = new HashSet<>();


}
