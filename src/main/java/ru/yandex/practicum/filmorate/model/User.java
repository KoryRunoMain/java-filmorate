package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

/**
 * User.
 */
@Data
@AllArgsConstructor
public class User {

    private Integer id;
    @NotNull(message = "электронная почта не может быть пустой")
    @Email(message = "почтовый адрес должен содержать символ @")
    private String email;
    @NotNull(message = "логин не может быть пустым")
    // реализовать -> логин не может быть пустым и содержать пробелы
    private String login;
    // реализовать -> имя для отображения может быть пустым — в таком случае будет использован логин
    private String name;
    @PastOrPresent(message = "дата рождения не может быть в будущем")
    private LocalDate birthday;

}
