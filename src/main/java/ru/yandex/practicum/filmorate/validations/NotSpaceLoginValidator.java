package ru.yandex.practicum.filmorate.validations;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotSpaceLoginValidator implements ConstraintValidator<NotSpaceConstraint, String> {

    @Override
    public void initialize(NotSpaceConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.hasText(s)) {
            return !StringUtils.containsWhitespace(s);
        }
        return true;
    }
}
