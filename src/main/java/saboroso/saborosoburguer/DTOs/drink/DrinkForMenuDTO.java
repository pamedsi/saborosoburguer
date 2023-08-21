package saboroso.saborosoburguer.DTOs.drink;

import java.math.BigDecimal;

public record DrinkForMenuDTO (
        String identifier,
        String title,
        Integer ml,
        BigDecimal price
){}
