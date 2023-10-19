package saboroso.saborosoburguer.DTO.portion;

import jakarta.validation.constraints.NotBlank;
import jdk.jfr.BooleanFlag;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public record PortionDTO(
        String identifier,
        @NotBlank
        String title,
        @NumberFormat
        @NotBlank
        BigDecimal price,
        @NotBlank
        String description,
        String pic,
        @BooleanFlag
        Boolean inStock
) {}
