package ru.yandex.practicum.filmorate.storage.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.dao.UserDao;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User user) {
        String sql = "INSERT INTO users (email, login, name, birthday) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                user.getEmail(),
                user.getLogin(),
                user.getName(),
                user.getBirthday());
        User newUser = jdbcTemplate.queryForObject(
                "SELECT id," +
                        "email, " +
                        "login, " +
                        "name, " +
                        "birthday" +
                        "WHERE email = ?", new Object[]{user.getEmail()},
                this::mapRow
        );
        log.trace("В базу добален пользователь, id: " + user.getId());
        return newUser;
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public Optional<User> get(long id) {
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
    public boolean isElementContains(long id) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(
                "SELECT id, " +
                        "email, " +
                        "login, " +
                        "name, " +
                        "birthday, " +
                        "FROM users",
                this::mapRow
        );
    }

    @Override
    public Optional<User> delete(long id) {
        SqlRowSet userRow = jdbcTemplate.queryForRowSet(
                "DELETE " +
                        "FROM users " +
                        "WHERE id = ?", id
        );
        log.info("Пользователь с id: " + id + "был удален");
        return Optional.of((User) userRow);
    }

    private User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(
                resultSet.getLong("id"));
        user.setEmail(
                resultSet.getString("email"));
        user.setLogin(
                resultSet.getString("login"));
        user.setName(
                resultSet.getString("name"));
        user.setBirthday(
                resultSet.getDate("birthday").toLocalDate());
        return user;
    }

}
