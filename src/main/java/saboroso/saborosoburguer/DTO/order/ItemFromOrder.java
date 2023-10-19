package saboroso.saborosoburguer.DTO.order;

import java.math.BigDecimal;
import java.util.List;

public interface ItemFromOrder {
    String identifier();
    List<String> addOnsIdentifiers();
    String obs();
    BigDecimal itemSoldBy();
}