package ru.yandex.practicum.filmorate.validations;

import ru.yandex.practicum.filmorate.models.Film;
import javax.validation.ValidationException;
import java.time.LocalDate;

public class ValidateFilm {

    public void validateFilm(Film film) {
        if (film.getName() == null || film.getName().isEmpty()) {
            // добавить ЛОГ
            throw new ValidationException("Название не может быть пустым");
        }
        if (film.getDescription() != null && film.getDescription().length() > 200) {
            // добавить ЛОГ
            throw new ValidationException("Максимальная длина описания — 200 символов");
        }
        if (film.getReleaseDate() != null && film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            // добавить ЛОГ
            throw new ValidationException("Ошибка. Дата релиза — не раньше 28 декабря 1895 года;");
        }
        if (film.getDuration() <= 0) {
            // добавить ЛОГ
            throw new ValidationException("Продолжительность фильма должна быть положительной");
        }
    }

}
