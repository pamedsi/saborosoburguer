package saboroso.saborosoburguer.DTO.order.getOrder;

import java.math.BigDecimal;
import java.util.List;

public record BurgerForGetOrderDTO(
        String title,
        List<AddOnForGetOrderDTO> addOns,
        BreadForGetOrderDTO bread,
        ComboForGetDTO combo,
        String obs,
        BigDecimal burgerSoldBy
){}
