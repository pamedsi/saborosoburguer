package saboroso.saborosoburguer.DTO.order.postOrder;

import java.math.BigDecimal;
import java.util.List;

public record BurgerForPostOrderDTO(
        String identifier,
        List<String> addOnsIdentifiers,
        String breadIdentifier,
        String comboIdentifier,
        String obs,
        BigDecimal itemSoldBy
){}