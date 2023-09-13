package saboroso.saborosoburguer.DTOs.burger;

import saboroso.saborosoburguer.DTOs.category.CategoryDTO;
import saboroso.saborosoburguer.DTOs.ingredient.IngredientDTO;

import java.math.BigDecimal;
import java.util.List;

public record BurgerManagementDTO(
        String identifier,
        CategoryDTO category,
        String title,
        BigDecimal price,
        String pic,
        Boolean inStock,
        List<IngredientDTO> ingredients
) {}
