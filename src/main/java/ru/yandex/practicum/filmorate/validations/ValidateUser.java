package ru.yandex.practicum.filmorate.validations;

import ru.yandex.practicum.filmorate.models.User;
import javax.validation.ValidationException;
import java.time.LocalDate;

public class ValidateUser {

    public void validateUser(User user) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            // добавить ЛОГ
            throw new ValidationException("Электронная почта не может быть пустой. пример: example@example.com");
        }
        if (user.getLogin() == null || user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
            // добавить ЛОГ
            throw new ValidationException("Логин не может быть пустым и содержать пробелы");
        }
        if (user.getBirthday() != null && user.getBirthday().isAfter(LocalDate.now())) {
            // добавить ЛОГ
            throw new ValidationException("Дата рождения не может быть у будущем");
        }
    }

}
