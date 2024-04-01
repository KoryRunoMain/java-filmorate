package ru.yandex.practicum.filmorate.storage.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.models.User;
import ru.yandex.practicum.filmorate.storage.dao.UserDao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@Primary
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // USER.Создать пользователя в БД
    @Override
    public User create(User user) {
        String insertQuery = "INSERT INTO users (email, login, name, birthday) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(insertQuery,
                user.getEmail(),
                user.getLogin(),
                user.getName(),
                Date.valueOf(user.getBirthday()));
        String selectQuery = "SELECT id, email, login, name, birthday " +
                "FROM users " +
                "WHERE email=?";
        User newUser = jdbcTemplate.queryForObject(selectQuery, this::mapRow, user.getEmail());
        log.trace("Пользователь c {} идентификатором добавлен в БД.", user.getId());
        return newUser;
    }

    // USER.Обновить данные пользователя в БД
    @Override
    public User update(User user) {
        String insertQuery = "UPDATE users " +
                "SET email=?, login=?, name=?, birthday=? " +
                "WHERE id=?";
        jdbcTemplate.update(insertQuery,
                user.getEmail(),
                user.getLogin(),
                user.getName(),
                user.getBirthday(),
                user.getId());
        log.info("Пользователь с идентификатором {} обновлен.", user.getId());
        return getById(user.getId());
    }

    // USER.Получить пользователя по id из БД
    @Override
    public User getById(long userId) {
        SqlRowSet userRow = jdbcTemplate.queryForRowSet("SELECT * FROM users " +
                "WHERE id=?", userId);
        if (!userRow.next()) {
            log.info("Пользователь с идентификатором {} не найден.", userId);
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

    // USER.Получить список пользователей из БД
    @Override
    public List<User> getAll() {
        String getAllQuery = "SELECT id, email, login, name, birthday " +
                "FROM users";
        return jdbcTemplate.query(getAllQuery, this::mapRow);
    }

    // USER.Удалить пользователя по id из БД
    @Override
    public void deleteById(long userId) {
        String deleteQuery = "DELETE FROM users WHERE id=?";
        int row = jdbcTemplate.update(deleteQuery, userId);
        if (row <= 0) {
            log.info("Пользователь с идентификатором {} не найден.", userId);
        }
        log.info("Пользователь с идентификатором {} был удален.", userId);
    }

    // USER.Отображение данных полученных из БД на объект класса USER
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
