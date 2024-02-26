package ru.yandex.practicum.filmorate.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilmControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName(value = "Тест 1 запрос на проверку верно заполненных полей")
    public void test_1_createFilmValidData() {
        Film film = new Film(1L, "N1", "D1",
                LocalDate.of(2000, 1, 1), 100);

        ResponseEntity<Film> response = restTemplate.postForEntity("/films", film, Film.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName(value = "Тест 2 запрос на проверку неверно заполненных полей")
    public void test_2_createFilmInValidData() {
        Film film = new Film(null, null, "D1",
                LocalDate.of(9999, 1, 1), 100);

        ResponseEntity<Film> response = restTemplate.postForEntity("/films", film, Film.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


}