package ru.yandex.practicum.filmorate.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotSpaceLoginValidator.class)
@Documented
public @interface NotSpaceConstraint {

    // Устанавливаем значение по умолчанию
    String message() default "{NotSpaceLoginValidator.invalid}";

    // Группируем ограничения и устанавливаем пустой массив классов групп по умолчанию
    Class<?>[] groups() default { };

    // Ассоциируемм и устанавливаем пустой массив классов метаданных по умолчанию
    Class<? extends Payload>[] payload() default { };
}
