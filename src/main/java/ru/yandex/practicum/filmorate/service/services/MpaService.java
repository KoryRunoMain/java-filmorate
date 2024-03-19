package ru.yandex.practicum.filmorate.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.service.IMpaService;
import ru.yandex.practicum.filmorate.service.IService;
import ru.yandex.practicum.filmorate.storage.dao.MPADao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MpaService implements IMpaService {
    private final MPADao mpaDao;

    @Autowired
    public MpaService(MPADao mpaDao) {
        this.mpaDao = mpaDao;
    }


    @Override
    public MPARating getById(long id) {
        return mpaDao.getById(id);
    }

    public List<MPARating> getAll() {
        return mpaDao.getAll().stream()
                .sorted(Comparator.comparing(MPARating::getId))
                .collect(Collectors.toList());
    }

    @Override
    public MPARating create(MPARating mpaRating) {
        throw new UnsupportedOperationException("Добавить жанр можно в файле data.sql");
    }

    @Override
    public MPARating update(MPARating mpaRating) {
        throw new UnsupportedOperationException("Добавить жанр можно в файле data.sql");
    }

}
