package saboroso.saborosoburguer.DTOs.burger;

import saboroso.saborosoburguer.DTOs.ingredient.IngredientForMenuDTO;
import saboroso.saborosoburguer.model.BurgerCategory;

import java.math.BigDecimal;
import java.util.List;
public record BurgerForMenuDTO (
        String identifier,
        BurgerCategory category,
        String title,
        BigDecimal price,
        String pic,
        List<IngredientForMenuDTO> ingredients
) {}
