package saboroso.saborosoburguer.DTO.order.getOrder;

import saboroso.saborosoburguer.models.OrderStatus;
import saboroso.saborosoburguer.models.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public record OrderForGetDTO (
        String identifier,
        ZonedDateTime timeOfPurchase,
        String orderCode,
        OrderStatus status,
        String clientName,
        String clientPhoneNumber,
        String address,
        List<BurgerForGetOrderDTO> burgers,
        List<PortionForGetOrderDTO> portions,
        List<DrinkAndQuantityForGetDTO> drinks,
        PaymentMethod paymentMethod,
        String howClientPaid,
        BigDecimal totalPaid
) {}
