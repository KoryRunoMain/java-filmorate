package ru.yandex.practicum.filmorate.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import validations.ReleaseDateConstraint;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Film.
 */
@Data
@AllArgsConstructor
public class Film {

    private Integer id;

    @NotNull(message = "название не может быть null")
    private String name;

    @Size(max = 200, message = "максимальная длина описания — 200 символов")
    private String description;

    @NotNull(message = "релиз не может быть null")
    @ReleaseDateConstraint(message = "Дата релиза должна быть после 28 декабря 1895 года")
    private LocalDate releaseDate;

    @NotNull(message = "продолжительность не может быть null")
    @Positive(message = "продолжительность фильма должна быть положительной")
    private long duration;

}
