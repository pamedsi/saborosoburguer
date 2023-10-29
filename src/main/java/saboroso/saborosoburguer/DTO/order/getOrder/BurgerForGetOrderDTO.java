package saboroso.saborosoburguer.DTO.order.getOrder;

import java.math.BigDecimal;
import java.util.List;

public record BurgerForGetOrderDTO(
        String title,
        BreadForGetOrderDTO bread,
        ComboForGetDTO combo,
        List<AddOnForGetOrderDTO> addOns,
        String obs,
        BigDecimal burgerSoldBy
){}
