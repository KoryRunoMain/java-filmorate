package ru.yandex.practicum.filmorate.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@EqualsAndHashCode(exclude = "isCommon")
public class Friend {

    @NotNull
    private long userId;

    @NotNull
    private boolean isCommon;

}
