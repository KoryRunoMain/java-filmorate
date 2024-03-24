package ru.yandex.practicum.filmorate.service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.OperationNotFoundException;
import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.service.IMPAService;
import ru.yandex.practicum.filmorate.service.verifyService.IVerifyMpa;
import ru.yandex.practicum.filmorate.storage.dao.MPADao;

import java.util.List;

@Slf4j
@Service
public class MPAService implements IMPAService {
    private final MPADao mpaDao;
    private final IVerifyMpa verify;

    @Autowired
    public MPAService(MPADao mpaDao, IVerifyMpa verify) {
        this.mpaDao = mpaDao;
        this.verify = verify;
    }

    // MPARating.Создать рейтинг
    @Override
    public MPARating create(MPARating mpaRating) {
        log.info("Добавить рэйтинг можно в файле data.sql");
        throw new OperationNotFoundException("Добавить рейтинг можно в файле data.sql");
    }

    // MPARating.Обновить рейтинг
    @Override
    public MPARating update(MPARating mpaRating) {
        log.info("Обновить рэйтинг можно в файле data.sql");
        throw new OperationNotFoundException("Обновить рейтинг можно в файле data.sql");
    }

    // MPARating.Получить рейтинг по id
    @Override
    public MPARating getById(long id) {
        // Проверка
        verify.verifyMpaRating((int) id);
        // Получение
        MPARating mpaRating = mpaDao.getById((int) id);
        log.info("Получен рэйтинг с id: {}", id);
        return mpaRating;
    }

    // MPARating.Получить список рейтингов
    @Override
    public List<MPARating> getAll() {
        List<MPARating> mpaRatings = mpaDao.getAll();
        log.info("Получен список рэйтингов.");
        return mpaRatings;
    }

}
