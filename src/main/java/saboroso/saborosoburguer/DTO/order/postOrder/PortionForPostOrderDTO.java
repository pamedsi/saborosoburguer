package saboroso.saborosoburguer.DTO.order.postOrder;

import java.math.BigDecimal;
import java.util.List;

public record PortionForPostOrderDTO(
        String identifier,
        List<String> addOnsIdentifiers,
        String obs,
        BigDecimal itemSoldBy
){}
