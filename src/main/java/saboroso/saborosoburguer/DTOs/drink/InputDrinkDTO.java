package saboroso.saborosoburguer.DTOs.drink;

import jakarta.validation.constraints.NotBlank;
import jdk.jfr.BooleanFlag;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public record InputDrinkDTO (
        @NotBlank
        String title,
        @NumberFormat
        Integer ml,
        @NumberFormat
        BigDecimal price,
        @BooleanFlag
        Boolean inStock,
        @BooleanFlag
        Boolean deleted
){}
