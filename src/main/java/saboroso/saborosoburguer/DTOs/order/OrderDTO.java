package saboroso.saborosoburguer.DTOs.order;

import java.math.BigDecimal;
import java.util.List;

public record OrderDTO(
        String customerId,
        List<ItemAndQuantityDTO> burgers,
        List<ItemAndQuantityDTO> drinks,
        List<ItemAndQuantityDTO> portions,
        String Address,
        BigDecimal valueToPay
){}