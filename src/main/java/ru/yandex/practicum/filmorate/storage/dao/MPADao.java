package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.MPARating;

import java.util.List;

public interface MPADao {

    MPARating getById(int id);

    List<MPARating> getAll();

}
