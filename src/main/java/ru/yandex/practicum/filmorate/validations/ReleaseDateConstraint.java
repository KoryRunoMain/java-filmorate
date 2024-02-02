package ru.yandex.practicum.filmorate.validations;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReleaseDateCinema.class)
@Documented
public @interface ReleaseDateConstraint {

    String message() default "Дата релиза должна быть после {value}";

    Class<?>[] groups() default {};

    Class<?>[] payLoad() default {};
}
