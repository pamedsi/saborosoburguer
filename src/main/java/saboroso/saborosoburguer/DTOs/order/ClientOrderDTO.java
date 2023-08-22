package saboroso.saborosoburguer.DTOs.order;

import java.math.BigDecimal;
import java.util.List;

public record ClientOrderDTO (
        List<ItemAndQuantityDTO> burgers,
        List<ItemAndQuantityDTO> drinks,
        List<ItemAndQuantityDTO> portions,
        BigDecimal valueToPay
){}
