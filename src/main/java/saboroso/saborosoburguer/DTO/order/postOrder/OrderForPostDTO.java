package saboroso.saborosoburguer.DTO.order.postOrder;

import saboroso.saborosoburguer.models.OrderStatus;
import saboroso.saborosoburguer.models.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderForPostDTO(
        String orderCode,
        OrderStatus status,
        String clientName,
        String clientPhoneNumber,
        String addressToDeliver,
        List<BurgerForPostOrderDTO> burgers,
        List<PortionForPostOrderDTO> portions,
        List<DrinkAndQuantityForPostDTO> drinks,
        PaymentMethod paymentMethod,
        String howClientWillPay,
        BigDecimal totalToPay
){}