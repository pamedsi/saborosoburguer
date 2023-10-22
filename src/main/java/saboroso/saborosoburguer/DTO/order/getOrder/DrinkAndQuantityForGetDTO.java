package saboroso.saborosoburguer.DTO.order.getOrder;

import java.math.BigDecimal;

public record DrinkAndQuantityForGetDTO(
        String title,
        Integer ml,
        BigDecimal price,
        Integer quantity
) {}
