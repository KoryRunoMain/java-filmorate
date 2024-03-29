package ru.yandex.practicum.filmorate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MPARating implements Model {

    @NotNull
    private Long id; // идентификатор возрастного рейтинга для фильма

    @NotBlank
    private String name; // наименование возрастного рейтинга

}
