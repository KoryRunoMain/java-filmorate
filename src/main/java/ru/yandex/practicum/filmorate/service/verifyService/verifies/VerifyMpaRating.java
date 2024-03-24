package ru.yandex.practicum.filmorate.service.verifyService.verifies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.service.verifyService.IVerifyMpa;
import ru.yandex.practicum.filmorate.storage.dao.MPADao;

@Service
public class VerifyMpaRating implements IVerifyMpa {
    private final MPADao mpaDao;

    @Autowired
    public VerifyMpaRating(MPADao mpaDao) {
        this.mpaDao = mpaDao;
    }

    // MPARating.Проверить рейтинг в БД
    public void verifyMpaRating(int id) {
        try {
            mpaDao.getById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Рейтинг c id " + id + " не найден.");
        }
    }

}
