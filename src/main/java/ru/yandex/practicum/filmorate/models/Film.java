package ru.yandex.practicum.filmorate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film implements Model {

    @Positive
    private Long id; // id фильма

    @NotNull(message = "поле не может быть пустым")
    @NotBlank(message = "название не может быть пустым")
    private String name; // название фильма

    @NotNull(message = "поле не может быть пустым")
    @Size(min = 1, max = 200, message = "минимальная длина описания - 1 символ, " +
                                        "максимальная длина описания — 200 символов")
    private String description; // описание фильма

    @NotNull(message = "поле не может быть пустым")
    private LocalDate releaseDate; // дата выхода фильма

    @NotNull(message = "поле не может быть пустым")
    @Positive(message = "продолжительность фильма должна быть положительной")
    private long duration; // продолжительность фильма

    @NotNull(message = "поле не может быть пустым")
    private MPARating mpa; // значения оценок возрастного ограничения для фильма

    private final Set<Long> likes = new HashSet<>(); // лайки пользователя

    private final Set<Genre> genre = new HashSet<>(); // жанры фильмов

    public void addLike(long userId) {
        this.likes.add(userId);
    }

    public void deleteLike(long userId) {
        this.likes.remove(userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return duration == film.duration
                && Objects.equals(name, film.name)
                && Objects.equals(description, film.description)
                && Objects.equals(releaseDate, film.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, releaseDate, duration);
    }
}
