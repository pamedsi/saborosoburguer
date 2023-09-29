package saboroso.saborosoburguer.DTO.drink;

import java.math.BigDecimal;

public record DrinkDTO(
        String identifier,
        String title,
        Integer ml,
        BigDecimal price,
        Boolean inStock
){}
