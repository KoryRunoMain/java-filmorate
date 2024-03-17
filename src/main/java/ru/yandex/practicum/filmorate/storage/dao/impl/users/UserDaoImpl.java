package ru.yandex.practicum.filmorate.storage.dao.impl.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.dao.UserDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User create) {
        return null;
    }

    @Override
    public User getElementById(long id) {
        return null;
    }

    @Override
    public User update() {
        return null;
    }

    @Override
    public boolean isContains(long id) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User createUser(User newUser) {
        String sql = "INSERT INTO users (email, login, name, birthday) " +
                    "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                newUser.getEmail(),
                newUser.getLogin(),
                newUser.getName(),
                newUser.getBirthday());
        log.info("Пользователь создан id: " + newUser.getId());
        return newUser;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        SqlRowSet userRow = jdbcTemplate.queryForRowSet(
                "SELECT * " +
                    "FROM users " +
                    "WHERE id = ?", id
        );

        if (userRow.next()) {
            User user = new User(
                    userRow.getLong("id"),
                    userRow.getString("email"),
                    userRow.getString("login"),
                    userRow.getString("name"),
                    Objects.requireNonNull(userRow.getDate("birthday")).toLocalDate()
            );
            log.info("Найден пользователь: {} {}", user.getId(), user.getName());
            return Optional.of(user);
        }
        log.info("Пользователь с идентификатором {} не найден.", id);
        return Optional.empty();
    }

    @Override
    // Дописать еще условие что себя нельзя удалять
    public Optional<User> deleteUser(Long id) {
        SqlRowSet userRow = jdbcTemplate.queryForRowSet(
                "DELETE " +
                    "FROM users " +
                    "WHERE id = ?", id
        );
        log.info("Пользователь с id: " + id + "был удален");
        return Optional.of((User) userRow);
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
