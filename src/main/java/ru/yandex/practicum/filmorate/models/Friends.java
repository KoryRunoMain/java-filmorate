package ru.yandex.practicum.filmorate.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class Friends {

    @NotNull
    private Long userId;

    @NotNull
    private Long friendId;

    @NotNull
    private Boolean isCommon;

}
