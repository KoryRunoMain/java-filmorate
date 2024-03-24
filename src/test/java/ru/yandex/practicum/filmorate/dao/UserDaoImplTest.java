package ru.yandex.practicum.filmorate.dao;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.dao.impl.UserDaoImpl;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserDaoImplTest {
    private final JdbcTemplate jdbcTemplate;

    @Test
    public void testFindUserById() {
        User newUser = new User(1L, "user@email.ru", "koryruno", "Vladimir", LocalDate.of(1994, 1, 24));
        UserDaoImpl userDaoImpl = new UserDaoImpl(jdbcTemplate);
        userDaoImpl.create(newUser);
        User createdUser = userDaoImpl.getById(1);
        assertThat(createdUser)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(newUser);
    }

}