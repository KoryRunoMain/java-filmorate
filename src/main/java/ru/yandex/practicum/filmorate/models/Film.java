package ru.yandex.practicum.filmorate.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Film
 */
@Data
@AllArgsConstructor
public class Film {

    private Integer id;

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

}
