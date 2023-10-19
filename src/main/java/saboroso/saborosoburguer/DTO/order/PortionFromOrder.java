package saboroso.saborosoburguer.DTO.order;

import java.math.BigDecimal;
import java.util.List;

public record PortionFromOrder(
        String identifier,
        List<String> addOnsIdentifiers,
        String obs,
        BigDecimal itemSoldBy
)implements ItemFromOrder {}
