package saboroso.saborosoburguer.validations.burger;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BurgerCategoryValidator implements ConstraintValidator<ValidBurgerCategory, String> {
        @Override
        public boolean isValid(String burgerCategory, ConstraintValidatorContext context) {
            return
                    burgerCategory.equalsIgnoreCase("smash artesanal") ||
                    burgerCategory.equalsIgnoreCase("smash duplo");
        }
}

