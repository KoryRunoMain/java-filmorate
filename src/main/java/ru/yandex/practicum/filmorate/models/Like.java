package ru.yandex.practicum.filmorate.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class Like {

    @NotNull
    private Long userId;

    @NotNull
    private Long filmId;

}
