package ru.yandex.practicum.filmorate.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.service.IMPAService;
import ru.yandex.practicum.filmorate.storage.dao.MPADao;

import java.util.List;

@Service
public class MPAService implements IMPAService {
    private final MPADao mpaDao;

    @Autowired
    public MPAService(MPADao mpaDao) {
        this.mpaDao = mpaDao;
    }


    @Override
    public MPARating getById(long id) {
        mpaExist((int) id);
        return mpaDao.getById((int) id);
    }

    @Override
    public List<MPARating> getAll() {
        return mpaDao.getAll();
    }

    @Override
    public MPARating create(MPARating mpaRating) {
        throw new UnsupportedOperationException("Добавить жанр можно в файле data.sql");
    }

    @Override
    public MPARating update(MPARating mpaRating) {
        throw new UnsupportedOperationException("Добавить жанр можно в файле data.sql");
    }

    private void mpaExist(int id) {
        try {
            mpaDao.getById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Рейтинг c id " + id + " не найден.");
        }
    }

}
