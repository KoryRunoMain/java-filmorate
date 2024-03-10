package ru.yandex.practicum.filmorate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.yandex.practicum.filmorate.models.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class Genre implements Model {

    @Positive
    private Long id;

    @NotBlank
    private String name;
}
