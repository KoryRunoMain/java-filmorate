package ru.yandex.practicum.filmorate.service.verifyService.verifies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.service.verifyService.IVerifyGenre;
import ru.yandex.practicum.filmorate.storage.dao.GenreDao;

@Service
public class VerifyGenre implements IVerifyGenre {
    private final GenreDao genreDao;

    @Autowired
    public VerifyGenre(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    // GENRE.Проверить жанр в БД
    public void verifyGenre(int genreId) {
        try {
            genreDao.getById(genreId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Жанр c id " + genreId + " не найден.");
        }
    }

}
