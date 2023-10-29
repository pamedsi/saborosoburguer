package saboroso.saborosoburguer.DTO.order;

import saboroso.saborosoburguer.models.OrderStatus;

public record UpdateOrderStatus(
        String orderIdentifier,
        OrderStatus status
) {}
