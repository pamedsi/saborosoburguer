package saboroso.saborosoburguer.DTOs.combo;

import jakarta.validation.constraints.NotBlank;
import jdk.jfr.BooleanFlag;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public record ComboDTO(
        @NotBlank
        String identifier,
        @NotBlank
        String title,
        @NumberFormat
        BigDecimal price,
        @BooleanFlag
        Boolean inStock,
        @NotBlank
        String description
) {}
