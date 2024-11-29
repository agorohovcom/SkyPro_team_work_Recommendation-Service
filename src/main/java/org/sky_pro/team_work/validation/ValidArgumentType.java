package org.sky_pro.team_work.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE_USE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidArgumentTypeValidator.class)
public @interface ValidArgumentType {
    String message() default "Некорректный аргумент";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
