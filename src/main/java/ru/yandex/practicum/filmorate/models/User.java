package ru.yandex.practicum.filmorate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Model {

    private final Set<Long> friends = new HashSet<>(); // id пользователей в друзьях

    @Positive
    private Long id; // id пользователя

    @NotNull(message = "поле не может быть пустым")
    @NotBlank(message = "электронная почта не может быть пустой")
    private String email; // адрес электронной почты

    @NotNull(message = "поле не может быть пустым")
    @NotBlank(message = "логин не может быть пустым")
    private String login; // логин пользователя

    private String name; // имя пользователя

    @NotNull(message = "поле не может быть пустым")
    @Past(message = "дата рождения не может быть в будущем")
    private LocalDate birthday; // дата рождения

    public void addFriend(long userId) {
        this.friends.add(userId);
    }

    public void deleteFriend(long userId) {
        this.friends.remove(userId);
    }

}
