package saboroso.saborosoburguer.DTOs.portion;

import java.math.BigDecimal;

public record PortionDTO(
        String identifier,
        String title,
        BigDecimal price,
        String description,
        String pic,
        Boolean inStock
) {}
