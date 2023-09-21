package saboroso.saborosoburguer.DTOs.portion;

import jakarta.validation.constraints.NotBlank;
import jdk.jfr.BooleanFlag;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public record InputPortionDTO(
        @NotBlank
        String title,
        @NumberFormat
        @NotBlank
        BigDecimal price,
        @NotBlank
        String description,
        @BooleanFlag
        Boolean inStock
) {}
