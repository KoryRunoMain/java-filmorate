package ru.yandex.practicum.filmorate.storage.dao.impl.users;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.dao.UserDao;
import ru.yandex.practicum.filmorate.storage.dao.impl.films.FilmMapper;

import java.sql.SQLException;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public User createUser(User newUser) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User deleteUser(Long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return jdbcTemplate.query(
                "SELECT id, " +
                        "email, " +
                        "login, " +
                        "name, " +
                        "birthday, " +
                        "FROM users",
                new UserMapper()
        );
    }
}
