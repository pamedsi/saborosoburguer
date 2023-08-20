package saboroso.saborosoburguer.validations.burger;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BurgerCategoryValidator.class)
public @interface ValidBurgerCategory {
    String message() default "Invalid burger category";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
