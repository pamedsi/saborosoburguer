package saboroso.saborosoburguer.DTO.order.getOrder;

import java.math.BigDecimal;

public record AddOnForGetOrderDTO(
        String title,
        String pic,
        BigDecimal price
) {}
