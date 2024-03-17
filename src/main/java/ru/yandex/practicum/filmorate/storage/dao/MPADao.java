package ru.yandex.practicum.filmorate.storage.dao;

import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.service.IStorageService;

import java.util.List;

public interface MPADao extends IStorageService<User> {

    MPARating getMpaById(Long id);

    List<MPARating> getAllMpaRatings();

}
