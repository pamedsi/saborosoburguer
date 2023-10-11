package saboroso.saborosoburguer.DTO.burger;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import saboroso.saborosoburguer.DTO.category.CategoryDTO;
import saboroso.saborosoburguer.DTO.ingredient.IngredientDTO;

import java.math.BigDecimal;
import java.util.List;

public record BurgerDTO(
        @NotBlank(message = "Identifier is mandatory")
        String identifier,
        @NotNull(message = "Category is mandatory")
        CategoryDTO categoryDTO,
        @NotBlank(message = "Title is mandatory")
        String title,
        @NotNull(message = "Price is mandatory")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal price,
        String pic,
        @NotNull(message = "InStock is mandatory")
        Boolean inStock,
        List<@Valid IngredientDTO> ingredients
) {}
