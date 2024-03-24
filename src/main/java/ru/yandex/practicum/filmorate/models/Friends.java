package ru.yandex.practicum.filmorate.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friends {

    @NotNull
    private Long userId; // идентификатор пользователя

    @NotNull
    private Long friendId; // идентификатор пользователя в друзьях

    @NotNull
    private Boolean isCommon; // статус дружбы (true - друзья, false - нет)

}
