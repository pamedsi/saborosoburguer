package saboroso.saborosoburguer.DTO.order;

import saboroso.saborosoburguer.models.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderDTO(
        String clientName,
        String clientPhoneNumber,
        String addressToDeliver,
        List<BurgerFromOrder> burgers,
        List<PortionFromOrder> portions,
        List<DrinkAndQuantity> drinks,
        PaymentMethod paymentMethod,
        String howClientWillPay,
        BigDecimal totalToPay
){}