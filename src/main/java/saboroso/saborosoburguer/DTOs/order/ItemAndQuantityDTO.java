package saboroso.saborosoburguer.DTOs.order;
public record ItemAndQuantityDTO(
        String identifier,
        Long quantity,
        Boolean isCombo
) {}
