package saboroso.saborosoburguer.DTO.burger;

import jdk.jfr.BooleanFlag;
import org.springframework.format.annotation.NumberFormat;
import saboroso.saborosoburguer.DTO.ingredient.IngredientDTO;

import java.math.BigDecimal;
import java.util.List;

public record InputBurgerDTO(
        String title,
        String categoryIdentifier,
        @NumberFormat
        BigDecimal price,
        String pic,
        @BooleanFlag
        Boolean inStock,
        List<IngredientDTO> ingredients
)
{}
