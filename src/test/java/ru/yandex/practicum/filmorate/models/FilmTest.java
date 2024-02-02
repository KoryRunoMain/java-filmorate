package ru.yandex.practicum.filmorate.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;


public class FilmTest {

    Film film1 = new Film(1, "N1", "D1",
            LocalDate.of(2024, 1, 1), 100);


    @Test
    public void test_1_releaseDateConstraintValid() {
    }

    @Test
    public void test_1_releaseDateConstraintInValid() {
    }

    @Test
    public void test_2_positiveDurationValid() {
    }

    @Test
    public void test_3_positiveDurationInvalid() {
    }

}
