package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.models.MPARating;
import ru.yandex.practicum.filmorate.storage.dao.MPADao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MPAService {
    private final MPADao mpaDao;

    public MPAService(MPADao mpaDao) {
        this.mpaDao = mpaDao;
    }

    public MPARating getMpa(Long id) {
        return mpaDao.getMpaById(id);
    }

    public List<MPARating> getAllMpa() {
        return mpaDao.getAllMpaRatings().stream()
                .sorted(Comparator.comparing(MPARating::getId))
                .collect(Collectors.toList());
    }

}
