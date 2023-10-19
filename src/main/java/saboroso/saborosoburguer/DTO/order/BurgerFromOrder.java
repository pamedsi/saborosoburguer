package saboroso.saborosoburguer.DTO.order;

import java.math.BigDecimal;
import java.util.List;

public record BurgerFromOrder (
        String identifier,
        List<String> addOnsIdentifiers,
        String breadIdentifier,
        String comboIdentifier,
        String obs,
        BigDecimal itemSoldBy
) implements ItemFromOrder {}
