package ru.yandex.practicum.filmorate.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * User
 */
@Data
@Slf4j
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

    public User(Integer id, String email, String login, LocalDate birthday, String name) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.birthday = birthday;
        this.name = getNameIfEmpty(name, login);
    }

    // имя для отображения может быть пустым — в таком случае будет использован логин
    private String getNameIfEmpty(String name, String login) {
        if (name == null || name.isBlank() || name.trim().isEmpty()) {
            log.info("Поле имени использует адрес эл.почты");
            return login;
        }
        return name;
    }

}
