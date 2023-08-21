package saboroso.saborosoburguer.DTOs.portion;

import java.math.BigDecimal;

public record PortionForMenuDTO (
        String identifier,
        String title,
        BigDecimal price,
        String description
) {}
