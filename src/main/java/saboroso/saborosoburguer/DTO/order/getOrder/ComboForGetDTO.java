package saboroso.saborosoburguer.DTO.order.getOrder;

import java.math.BigDecimal;

public record ComboForGetDTO (
        String title,
        String pic,
        String description,
        BigDecimal price
){}
