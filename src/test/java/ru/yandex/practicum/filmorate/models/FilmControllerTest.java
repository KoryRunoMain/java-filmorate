package ru.yandex.practicum.filmorate.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.yandex.practicum.filmorate.controllers.FilmController;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilmControllerTest {

    private FilmController filmController;

    @BeforeEach
    void setUp() {
        filmController = new FilmController();
    }

    @Test
    @DisplayName(value = "Тест GET запроса на получение всех фильмов")
    public void test_1_getFilms() {
        ResponseEntity<List<Film>> response = filmController.getFilms();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    @DisplayName(value = "Тест POST запроса на добавление фильма")
    public void test_2_createFilmValid() {
        Film film = new Film(1, "N1", "D1",
                LocalDate.of(2024, 1, 1), 100);
        ResponseEntity<Film> response = filmController.createFilm(film);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(film, response.getBody());
    }

    @Test
    @DisplayName(value = "Тест POST запроса на добавление фильма с неверно заполненными полями")
    public void test_3_createFilmInValid() {
        Film film = new Film(1, "", "",
                LocalDate.of(2024, 1, 1), -100);
        ResponseEntity<Film> response = filmController.createFilm(film);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(film, response.getBody());
    }

    @Test
    @DisplayName(value = "Тест PUT запроса на добавление или обновление фильма")
    public void test_4_updateOrCreateFilmValid() {
        Film film = new Film(1, "N1", "D1",
                LocalDate.of(2024, 1, 1), 100);
        ResponseEntity<Film> response = filmController.updateOrCreateFilm(film);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(film, response.getBody());
    }

    @Test
    @DisplayName(value = "Тест PUT запроса на добавление или обновление уже существующего фильма")
    public void test_5_updateOrCreateFilmInValid() {
        Film film = new Film(1, "N1", "D1",
                LocalDate.of(2024, 1, 1), 100);
        filmController.createFilm(film);

        Film plagiatFilm = new Film(999, "N1", "D1",
                LocalDate.of(2024, 1, 1), 100);
        ResponseEntity<Film> response = filmController.updateOrCreateFilm(plagiatFilm);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(plagiatFilm, response.getBody());
    }

}