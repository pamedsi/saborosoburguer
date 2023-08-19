package saboroso.saborosoburguer.DTOs;

import jakarta.validation.constraints.NotBlank;
import jdk.jfr.BooleanFlag;
import org.springframework.format.annotation.NumberFormat;

public record inputIngredientDTO(
        @NotBlank
        String title,
        @NumberFormat
        Integer grams,
        @BooleanFlag
        Boolean inStock
) {}
