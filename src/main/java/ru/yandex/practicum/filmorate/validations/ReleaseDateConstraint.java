package ru.yandex.practicum.filmorate.validations;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReleaseDateCinema.class)
@Documented
public @interface ReleaseDateConstraint {

    // Устанавливаем значение по умолчанию
    String message() default "Дата релиза должна быть после {value}";

    // Группируем ограничения и устанавливаем пустой массив классов групп по умолчанию
    Class<?>[] groups() default {};

    // Ассоциируемм и устанавливаем пустой массив классов метаданных по умолчанию
    Class<?>[] payLoad() default {};
}
