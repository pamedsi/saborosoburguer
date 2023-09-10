package saboroso.saborosoburguer.DTOs.burger;

import jdk.jfr.BooleanFlag;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public record InputBurgerDTO(
        String categoryIdentifier,
        String title,
        @NumberFormat
        BigDecimal price,
        String pic,
        @BooleanFlag
        Boolean inStock,
        @BooleanFlag
        Boolean deleted
)
{}
