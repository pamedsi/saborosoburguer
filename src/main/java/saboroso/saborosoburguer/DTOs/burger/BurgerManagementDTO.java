package saboroso.saborosoburguer.DTOs.burger;

import saboroso.saborosoburguer.DTOs.CategoryDTO;
import saboroso.saborosoburguer.DTOs.ingredient.IngredientForMenuDTO;

import java.math.BigDecimal;
import java.util.List;
public record BurgerManagementDTO(
        String identifier,
        CategoryDTO category,
        String title,
        BigDecimal price,
        String pic,
        Boolean inStock,
        List<IngredientForMenuDTO> ingredients
) {}
