package saboroso.saborosoburguer.DTOs.burger;

import jdk.jfr.BooleanFlag;
import org.springframework.format.annotation.NumberFormat;
import saboroso.saborosoburguer.validations.burger.ValidBurgerCategory;

import java.math.BigDecimal;

public record InputBurgerDTO(
        @ValidBurgerCategory
        String category,
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
