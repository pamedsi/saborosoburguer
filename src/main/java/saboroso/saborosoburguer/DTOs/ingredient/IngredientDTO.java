package saboroso.saborosoburguer.DTOs.ingredient;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;

public record IngredientDTO(
        String identifier,
        @NotBlank
        String title,
        @Min(0)
        Integer grams,
        @BooleanFlag
        @NotNull
        Boolean inStock
) {}