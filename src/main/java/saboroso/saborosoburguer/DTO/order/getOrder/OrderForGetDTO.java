package saboroso.saborosoburguer.DTO.order.getOrder;

import saboroso.saborosoburguer.models.OrderStatus;
import saboroso.saborosoburguer.models.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderForGetDTO (
        String orderCode,
        OrderStatus status,
        String clientName,
        String clientPhoneNumber,
        String addressToDeliver,
        List<BurgerForGetOrderDTO> burgers,
        List<PortionForGetOrderDTO> portions,
        List<DrinkAndQuantityForGetDTO> drinks,
        PaymentMethod paymentMethod,
        String howClientWillPay,
        BigDecimal totalToPay
) {}