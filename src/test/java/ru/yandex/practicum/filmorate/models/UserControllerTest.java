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
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

//    @Test
//    @DisplayName(value = "Тест 1 запрос на проверку верно заполненных полей")
//    public void test_1_createUserValidData() {
//        User user = new User(1L, "best@ofthebest.best", "best", "Vladimir",
//                LocalDate.of(1994, 1, 24));
//
//        ResponseEntity<User> response = restTemplate.postForEntity("/ru/yandex/practicum/filmorate/storage/dao/impl/users", user, User.class);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//    }
//
//    @Test
//    @DisplayName(value = "Тест 2 запрос на проверку неверно заполненных полей")
//    public void test_2_createUserInvalidData() {
//        User user = new User(null, " ", null, "Vladimir",
//                LocalDate.of(9999, 1, 24));
//
//        ResponseEntity<User> response = restTemplate.postForEntity("/ru/yandex/practicum/filmorate/storage/dao/impl/users", user, User.class);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//    }

}
