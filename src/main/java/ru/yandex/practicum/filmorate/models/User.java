package ru.yandex.practicum.filmorate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

/**
 * User.
 */
@Data
@AllArgsConstructor
@Slf4j
public class User {

    private Integer id;

    @NotNull(message = "электронная почта не может быть пустой")
    @Email(message = "почтовый адрес должен содержать символ @")
    private String email;

    @NotNull(message = "логин не может быть null")
    @NotBlank(message = "логин не может содержать пробелы")
    private String login;

    private String name;

    @PastOrPresent(message = "дата рождения не может быть в будущем")
    private LocalDate birthday;

}
