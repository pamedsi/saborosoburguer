package saboroso.saborosoburguer.DTOs.drink;

import java.math.BigDecimal;

public record DrinkDTO(
        String identifier,
        String title,
        Integer ml,
        BigDecimal price,
        Boolean inStock
){}
