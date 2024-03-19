package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.MPARating;

import java.util.List;
import java.util.Optional;

public interface MPADao {

    MPARating getById(long id);

    List<MPARating> getAll();

}
