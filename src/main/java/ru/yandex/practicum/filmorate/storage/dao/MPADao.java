package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.MPARating;

import java.util.List;
import java.util.Optional;

public interface MPADao {

    Optional<MPARating> get(long id);

    boolean isContains(long id);

    List<MPARating> getAll();

}
