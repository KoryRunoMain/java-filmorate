package ru.yandex.practicum.filmorate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * User
 */
@Data
@AllArgsConstructor
public class User {

    private Integer id;

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

}
