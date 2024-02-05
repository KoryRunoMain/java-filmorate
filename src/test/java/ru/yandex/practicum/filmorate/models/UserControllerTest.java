package ru.yandex.practicum.filmorate.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.yandex.practicum.filmorate.controllers.UserController;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    User user = new User(1, "best@ofthebest.best", "best",
            LocalDate.of(1994,1,24), "Vladimir");

    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController();
    }

    @Test
    @DisplayName(value = "Тест GET запроса на получение всех пользователей")
    public void test_1_getUsers() {
        ResponseEntity<List<User>> response = userController.getUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    @DisplayName(value = "Тест POST запроса на авторизацию пользователя")
    public void test_2_createUserValid() {
        User user = new User(1, "best@ofthebest.best", "best",
                LocalDate.of(1994,1,24), "Vladimir");
        ResponseEntity<User> response = userController.createUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());
    }

    @Test
    @DisplayName(value = "Тест POST запроса на авторизацию пользователя который уже существует")
    public void test_3_createUserExisting() {
        User user = new User(1, "best@ofthebest.best", "best",
                LocalDate.of(1994,1,24), "Vladimir");
        userController.createUser(user);

        ResponseEntity<User> response = userController.createUser(user);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());
    }

    @Test
    @DisplayName(value = "Тест POST запроса с неверной датой рождения")
    public void test_4_createUserWithWrongDate() {
        User user = new User(1, "best@ofthebest.best", "best",
                LocalDate.of(2025,1,24), "Vladimir");
        ResponseEntity<User> response = userController.createUser(user);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());
    }

    @Test
    @DisplayName("Тест POST запроса с пустым полем 'name', в поле должен быть записан login")
    public void test_5_createUserWithEmptyName() {
        User user = new User(1, "best@ofthebest.best", "best",
                LocalDate.of(1994,1,24), " ");
        ResponseEntity<User> response = userController.createUser(user);
        assertEquals(user.getName(), user.getLogin());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());
    }

    @Test
    @DisplayName(value = "Тест PUT запроса на обновление или добавление пользователя")
    public void test_6_updateOrCreateUser() {
        User user = new User(1, "best@ofthebest.best", "best",
                LocalDate.of(1994,1,24), "Vladimir");
        ResponseEntity<User> response = userController.updateOrCreateUser(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());
    }

    @Test
    @DisplayName(value = "Тест PUT запроса на обновление или добавление существующего пользователя")
    public void test_7_updateOrCreateUserExisting() {
        User user = new User(1, "best@ofthebest.best", "best",
                LocalDate.of(1994,1,24), "Vladimir");
        userController.updateOrCreateUser(user);

        ResponseEntity<User> response = userController.updateOrCreateUser(user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());
    }

}
