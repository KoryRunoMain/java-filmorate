package ru.yandex.practicum.filmorate.storage.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.dao.UserDao;

import javax.validation.constraints.Negative;
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
        jdbcTemplate.update(sql,
                user.getEmail(),
                user.getLogin(),
                user.getName(),
                user.getBirthday());
        User newUser = jdbcTemplate.queryForObject(
                "SELECT id, email, login, name, birthday" +
                    "WHERE email = ?",
//                new Object[]{user.getEmail()},
                this::mapRow);
        log.trace("В базу добален пользователь c {} идентификатором.", user.getId());
        return newUser;
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE users " +
                "SET email = ?, login = ?, name = ?, birthday = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql,
                user.getEmail(),
                user.getLogin(),
                user.getName(),
                user.getBirthday(),
                user.getId());
        log.info("Пользователь с идентификатором {} обновлен.", user.getId());
        return user;
    }

    @Override
    public User getById(long id) {
        SqlRowSet userRow = jdbcTemplate.queryForRowSet(
                "SELECT * FROM users WHERE id = ?", id);
        if (!userRow.next()) {
            log.info("Пользователь с идентификатором {} не найден.", id);
            throw new NotFoundException("Пользователь не найден.");
        }
        User user = new User(
            userRow.getLong("id"),
            userRow.getString("email"),
            userRow.getString("login"),
            userRow.getString("name"),
            Objects.requireNonNull(userRow.getDate("birthday")).toLocalDate());
        log.info("Найден пользователь: {} {}", user.getId(), user.getName());
        return user;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(
                "SELECT id, email, login, name, birthday, " +
                    "FROM users",
                this::mapRow);
    }

    @Override
    public User deleteById(long id) {
        SqlRowSet userRow = jdbcTemplate.queryForRowSet(
                "DELETE " +
                        "FROM users " +
                        "WHERE id = ?", id);
        log.info("Пользователь с идентификатором {} был удален.", id);
        return (User) userRow;
    }

    private User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setEmail(resultSet.getString("email"));
        user.setLogin(resultSet.getString("login"));
        user.setName(resultSet.getString("name"));
        user.setBirthday(resultSet.getDate("birthday").toLocalDate());
        return user;
    }

}
