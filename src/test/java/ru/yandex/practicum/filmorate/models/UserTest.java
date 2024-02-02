package ru.yandex.practicum.filmorate.models;

import org.junit.Test;

import java.time.LocalDate;

public class UserTest {

    User user = new User(1, "user@yandex.org", "username", "N1",
            LocalDate.of(2024, 1, 1));

    @Test
    public void test_1_validUser() {
    }

    @Test
    public void test_2_inValidUser() {
    }

    @Test
    public void test_3_blankLogin() {
    }

    @Test
    public void test_4_futureBirthday() {
    }

}
