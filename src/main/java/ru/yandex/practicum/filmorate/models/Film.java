package ru.yandex.practicum.filmorate.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
public class Film implements Model {

    private final Set<Long> likes = new HashSet<>();
    private Long id;

    @NotNull(message = "название не может быть null")
    @NotBlank(message = "название не может быть пустым")
    private String name;

    @Size(max = 200, message = "максимальная длина описания — 200 символов")
    @NotNull(message = "описание не может быть null")
    private String description;

    @NotNull(message = "релиз не может быть null")
    private LocalDate releaseDate;

    @NotNull(message = "продолжительность не может быть null")
    @Positive(message = "продолжительность фильма должна быть положительной")
    private long duration;


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
