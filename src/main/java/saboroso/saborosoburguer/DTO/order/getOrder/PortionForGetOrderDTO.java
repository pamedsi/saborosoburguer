package saboroso.saborosoburguer.DTO.order.getOrder;

import java.math.BigDecimal;
import java.util.List;

public record PortionForGetOrderDTO(
        String title,
        String pic,
        List<AddOnForGetOrderDTO> addOns,
        String obs,
        BigDecimal price
) {}
