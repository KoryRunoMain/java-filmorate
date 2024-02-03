package ru.yandex.practicum.filmorate.validations;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.models.User;
import javax.validation.ValidationException;
import java.time.LocalDate;

@Slf4j
public class ValidateUser {

    public void validateUser(User user) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            log.info("ошбка проверки поля почты");
            throw new ValidationException("Электронная почта не может быть пустой. пример: example@example.com");
        }
        if (user.getLogin() == null || user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
            log.info("ошбка проверки поля логина");
            throw new ValidationException("Логин не может быть пустым и содержать пробелы");
        }
        if (user.getBirthday() != null && user.getBirthday().isAfter(LocalDate.now())) {
            log.info("ошбка проверки поля даты рождения");
            throw new ValidationException("Дата рождения не может быть у будущем");
        }
    }
}
