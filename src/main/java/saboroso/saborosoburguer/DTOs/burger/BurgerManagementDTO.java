package saboroso.saborosoburguer.DTOs.burger;

import saboroso.saborosoburguer.DTOs.ingredient.IngredientForMenuDTO;
import saboroso.saborosoburguer.model.BurgerCategory;

import java.math.BigDecimal;
import java.util.List;
public record BurgerManagementDTO(
        String identifier,
        BurgerCategory category,
        String title,
        BigDecimal price,
        String pic,
        Boolean inStock,
        List<IngredientForMenuDTO> ingredients
) {}
