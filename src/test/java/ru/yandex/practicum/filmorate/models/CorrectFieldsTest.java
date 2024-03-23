package ru.yandex.practicum.filmorate.models;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.service.services.FilmService;
import ru.yandex.practicum.filmorate.storage.dao.FilmDao;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CorrectFieldsTest {

    @Autowired
    private FilmService filmDao;

    @Test
    public void testFilmInitialization() {
        // Создаем новый объект класса Film
        Film source = new Film();
        source.setName("qwerqwerqwerqwer");
        source.setDescription("qwerqwerqwerwqerwqer");
        source.setReleaseDate(LocalDate.of(2020, 2, 21));
        source.setDuration(1000);
        source.setMpa(new MPARating(3L, "P"));

        Film savedFilm = filmDao.create(source);

        assertNotNull(savedFilm);
        assertNotNull(savedFilm.getId());
        assertEquals("qwerqwerqwerqwer", savedFilm.getName());
        assertEquals("qwerqwerqwerwqerwqer", savedFilm.getDescription());
        assertEquals(LocalDate.of(2020, 2, 21), savedFilm.getReleaseDate());
        assertEquals(1000, savedFilm.getDuration());
    }
}
